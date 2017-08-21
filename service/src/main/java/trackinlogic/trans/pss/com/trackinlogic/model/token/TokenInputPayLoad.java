package trackinlogic.trans.pss.com.trackinlogic.model.token;

/**
 * Created by Sekhar Madhiyazhagan on 8/20/2017.
 */

public class TokenInputPayLoad {
   public String grantType = "password";
   public String responseType = "id_token";
   public String scope = "PssUserMgtApi.Read";
    public String clientSecret = "ResourceOwner-F1C61FF1-A276-46B4-8899-C1CED4DF2A1C";
   public String clientId = "b03f80e2-c63d-40df-b200-4cbd3961172b";
   public String userName ;
   public String password;

   public TokenInputPayLoad(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }
}
