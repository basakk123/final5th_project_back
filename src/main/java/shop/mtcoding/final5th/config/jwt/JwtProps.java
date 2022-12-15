package shop.mtcoding.final5th.config.jwt;

public interface JwtProps {
    public static final String SUBJECT = "cos토큰";
    public static final String SECRET = "final5th";
    public static final String AUTH = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final Integer EXPIRESAT = 1000 * 60 * 60 * 24 * 10; // 10일
}
