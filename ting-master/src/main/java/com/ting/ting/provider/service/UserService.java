package com.ting.ting.provider.service;

import com.ting.ting.core.security.role.Role;
import com.ting.ting.core.service.UserServiceinterface;
import com.ting.ting.core.type.MBTIType;
import com.ting.ting.entity.User;
import com.ting.ting.exception.errors.CustomJwtRuntimeException;
import com.ting.ting.exception.errors.LoginFailedException;
import com.ting.ting.exception.errors.RegisterFailedException;
import com.ting.ting.provider.security.JwtAuthToken;
import com.ting.ting.provider.security.JwtAuthTokenProvider;
import com.ting.ting.repository.UserRepository;
import com.ting.ting.util.SHA256Util;
import com.ting.ting.web.dto.RequestUser;
import com.ting.ting.web.dto.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceinterface {
    private final UserRepository userRepository;
    private final JwtAuthTokenProvider jwtAuthTokenProvider;

    @Transactional
    @Override
    public void register(RequestUser.Register registerDto) {
        User user = userRepository.findByEmail(registerDto.getEmail());
        if (user != null) {
            //존재하는 아이디
            throw new RegisterFailedException();
        }
        //salt 생성
        String salt = SHA256Util.generateSalt();
        //sha256으로 솔트와 함께 암호화
        String encryptedPassword = SHA256Util.getEncrypt(registerDto.getPassword(), salt);
        //db에 넣기
        user = User.builder()
                .email(registerDto.getEmail())
                .password(encryptedPassword)
                .salt(salt)
                .name(registerDto.getName())
                .nickname(registerDto.getNickname())
                .birth(registerDto.getBirth())
                .build();
        userRepository.save(user);
    }

    @Transactional
    @Override
    public Optional<ResponseUser.Login> login(RequestUser.Login requestLoginDto){
        //이메일 user 꺼내기
        User user = userRepository.findByEmail(requestLoginDto.getEmail());
        //email에 맞는 user 없을 경우
        if (user == null){
            throw new LoginFailedException();
        }
            //salt 꺼내기
            String salt = user.getSalt();
            //salt와 requestLoginDto의 password랑 합치고 암호화
            String encryptedPassword = SHA256Util.getEncrypt(requestLoginDto.getPassword(), salt);
            //비교
            user = userRepository.findByEmailAndPassword(requestLoginDto.getEmail(), encryptedPassword);
            ResponseUser.Login login = null;
            if(user != null ){
                //로그인 성공
                String refreshToken = createRefreshToken(user.getEmail());
                login = ResponseUser.Login.builder()
                        .accessToken(createAccessToken(user.getEmail()))
                        .refreshToken(refreshToken)
                        .build();

                // 로그인 때마다 refresh token 업데이트
                user.changeRefreshToken(refreshToken);
            }
            else{
                //비밀 번호가 맞지 않음
                throw new LoginFailedException();
            }
        return  Optional.ofNullable(login);
    }

    @Transactional
    @Override
    public void Updatembti(MBTIType mbti, String email){
        //유저 엔티티를 꺼낸다
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new CustomJwtRuntimeException();
        }
        user.updatembti(mbti);
    }

    @Override
    public String createAccessToken(String id){
        //만료 기간 설정 (생성으로부터 30분동안)
        //Instant -> 컴퓨터가 알아보기 쉽게 표현하기 위한 형태
        Date expiredDate = Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant());
        //토큰 발급, 사용자 권한으로
        JwtAuthToken accessToken = jwtAuthTokenProvider.createAuthToken(id, Role.USER.getCode(),expiredDate);
        return accessToken.getToken();
    }

    @Override
    public String createRefreshToken(String id){
        //refreshToken 1년 기한으로 설정
        //이게 만료 되면 사용자는 새로 로그인해야함
        Date expiredDate = Date.from(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant());
        JwtAuthToken refreshToken = jwtAuthTokenProvider.createAuthToken(id, Role.USER.getCode(),expiredDate);
        return refreshToken.getToken();
    }

    @Override
    @Transactional
    public Page<ResponseUser.UserSearch> getUserSearch(String type, String keyword, Pageable pageable){
            if(type.equals("mbti")){
                //mbti로 검색했을 경우
                Page<User> users_mbti = userRepository.findByMbti(MBTIType.valueOf(keyword.toUpperCase()), pageable);
                return users_mbti.map(ResponseUser.UserSearch::of);
            }
                //nickname으로 검색했을 경우
                Page<User> user_nick = userRepository.findByNickname(keyword,pageable);
                return user_nick.map(ResponseUser.UserSearch::of);
    }
}
