package com.roommake.user.exception;

/**
 * 이미 사용된 닉네임 예외 클래스
 */
public class AlreadyUsedNicknameException extends HomeException {
    private static final long serialVersionUID = 1L;

    public AlreadyUsedNicknameException(String nickname) {
        super("해당 닉네임 '" + nickname + "'은(는) 이미 사용 중입니다.");
    }

    public AlreadyUsedNicknameException(String nickname, Throwable cause) {
        super("해당 닉네임 '" + nickname + "'은(는) 이미 사용 중입니다.", cause);
    }
}
