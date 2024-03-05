package vn.edu.stu.nodejs_api.DAO;
// JwtUtil.java

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class jwtUtill {

    // Define your secret key (should match the server's secret key)
    private static final String SECRET_KEY = "yourSecretKey";

    // Verify and decode JWT
    public static DecodedJWT verifyJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (Exception e) {
            // JWT verification failed
            e.printStackTrace();
            return null;
        }
    }

    // Extract user information from JWT
    public static String getUserIdFromJWT(String token) {
        DecodedJWT decodedJWT = verifyJWT(token);
        return (decodedJWT != null) ? decodedJWT.getClaim("userId").asString() : null;
    }

    // Example usage in your Android application
    public static void main(String[] args) {
        String jwtToken = "yourJwtTokenHere"; // Replace with the actual JWT received from the server

        // Verify and extract user information
        String userId = getUserIdFromJWT(jwtToken);

        if (userId != null) {
            System.out.println("User ID: " + userId);
            // Perform authorized actions here
        } else {
            System.out.println("JWT verification failed or expired.");
            // Handle unauthorized access
        }
    }
}
