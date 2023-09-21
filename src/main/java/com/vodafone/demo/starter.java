package com.vodafone.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class starter implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        JSONObject product1 = new JSONObject();
        product1.put("name","bvb");
        product1.put("status","optedIn");

        JSONObject product2 = new JSONObject();
        product2.put("name","data");
        product2.put("status","optedIn");

        JSONArray products =new JSONArray();
        products.add(product1);
        products.add(product2);

        JSONObject ctn1 = new JSONObject();
        ctn1.put("identifier","07624367524");
        ctn1.put("name","john");
        ctn1.put("address","paris");
        ctn1.put("subscription","payg");
        ctn1.put("products",products);

        JSONObject product11 = new JSONObject();
        product11.put("name","bvb");
        product11.put("status","optedIn");

        JSONObject product22 = new JSONObject();
        product22.put("name","data");
        product22.put("status","optedIn");

        JSONArray products2 =new JSONArray();
        products2.add(product11);
        products2.add(product22);

        JSONObject ctn2 = new JSONObject();
        ctn2.put("identifier","447879522013");
        ctn2.put("name","mendes");
        ctn2.put("address","italy");
        ctn2.put("subscription","paym");
        ctn2.put("products",products2);

        JSONArray ctns =new JSONArray();
        ctns.add(ctn1);
        ctns.add(ctn2);

        JSONObject ctnNode = new JSONObject();
        ctnNode.put("CTNS",ctns);


        JSONObject fln1 = new JSONObject();
        fln1.put("identifier","441876567289");
        fln1.put("name","john");
        fln1.put("address","paris");
        fln1.put("msisdn","07624367524");

        JSONObject fln2 = new JSONObject();
        fln2.put("identifier","018765345678");
        fln2.put("name","mendes");
        fln2.put("address","italy");
        fln2.put("msisdn","447879522013");

        JSONArray flns =new JSONArray();
        flns.add(fln1);
        flns.add(fln2);

        JSONObject flnNode = new JSONObject();
        flnNode.put("FLNS",flns);

        //Write JSON file
        try (FileWriter file = new FileWriter(new File("src\\main\\resources","ctns.json"))) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(ctnNode.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter(new File("src\\main\\resources","flns.json"))) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(flnNode.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
