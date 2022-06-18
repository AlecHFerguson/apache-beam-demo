package ai.alec.deeppow.pipelines;

import ai.alec.deeppow.options.JsonSpec;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestParse {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"stringArg\":\"hello\", \"intArg\":69}";

        JsonSpec spec = mapper.readValue(json, JsonSpec.class);
        System.out.println("stringArg = " + spec.stringArg);
    }
}
