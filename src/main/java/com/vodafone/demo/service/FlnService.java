package com.vodafone.demo.service;

import com.vodafone.demo.exceptions.NotFoundException;

import com.vodafone.demo.model.FlnDetails;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("fln")
public class FlnService implements BaseService{

    @Override
    public FlnDetails getDetails(String identifier) {
        JSONParser jsonParser = new JSONParser();
        List<FlnDetails> flnDetailsList = new ArrayList<>();

        try (FileReader reader = new FileReader("src/main/resources/flns.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject flnNode = (JSONObject) obj;
            JSONArray flns = (JSONArray) flnNode.get("FLNS");
            flnDetailsList = parseFlns(flns);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flnDetailsList.stream().filter(x-> x.getIdentifier().equals(identifier)).findFirst().orElseThrow(NotFoundException::new);    }

    private List<FlnDetails> parseFlns(JSONArray jsonFlns) {
        List<FlnDetails> flnDetails = new ArrayList<>();
        for (int i = 0 ; i< jsonFlns.size() ; i++){
            JSONObject ctn =(JSONObject) jsonFlns.get(i);
            FlnDetails details = new FlnDetails();
            details.setIdentifier((String) ctn.get("identifier"));
            details.setAddress((String) ctn.get("address"));
            details.setCustomerName((String) ctn.get("name"));
            details.setAttachedMsisdn((String) ctn.get("msisdn"));
            flnDetails.add(details);
        }
        return flnDetails;
    }
}
