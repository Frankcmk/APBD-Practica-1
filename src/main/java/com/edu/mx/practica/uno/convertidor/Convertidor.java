package com.edu.mx.practica.uno.convertidor;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;


public class Convertidor {

    /*
    *Metodo para convertir Clase a JSON
    * @param clase texto en formato clase para convertir
    * @return Clase convertida a JSON
     */
    public String convertirClaseToJson(Object persona){
        Gson gson = new Gson();
        String JSON = gson.toJson(persona);

        return JSON;
    }

    /*
     *Metodo para convertir XML a JSON
     * @param XML texto en formato clase para convertir
     * @return XML convertida a JSON
     */
    public String convertirXmlToJson(String xml){

        //Se toma el string tipo XML y se parsea a JSON
        JSONObject prueba = XML.toJSONObject(xml);
        //Se convierte el objeto JSON a un string
        String jsonfinal = prueba.toString();

        return jsonfinal;
    }

}
