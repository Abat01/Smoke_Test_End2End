package hooks;



import io.cucumber.java.Before;

import static base_urls.MedunnaBaseUrl.medunnaSetUp;

public class Hooks {

    @Before() //Testlerden on ishlet
    public void beforeApi(){
        medunnaSetUp();
    }

}
