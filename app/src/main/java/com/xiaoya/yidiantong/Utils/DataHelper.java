package com.xiaoya.yidiantong.utils;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smartydroid.android.starter.kit.app.StarterKitApp;
import com.smartydroid.android.starter.kit.model.entity.Entity;
import com.smartydroid.android.starter.kit.utilities.ACache;
import com.xiaoya.yidiantong.model.ExamRecord;
import com.xiaoya.yidiantong.model.QuestionCategory;
import com.xiaoya.yidiantong.model.VideoDetail;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Author: meyu
 * Date:   16/4/24
 * Email:  627655140@qq.com
 */
public class DataHelper {

    private List<QuestionCategory> getQuestionOptionTypes(int kem) {
        List<QuestionCategory> list = new ArrayList<>();
        if (kem == 1) {
            QuestionCategory questionCategory = new QuestionCategory();
        } else if (kem == 4) {

        }
        return list;
    }


    public static VideoDetail readXml(InputStream inStream) {
        // ①创建XML解析处理器
        XMLContentHandler contentHandler = new XMLContentHandler();
        try {
            // 创建一个SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // ②创建SAX解析器
            SAXParser parser = factory.newSAXParser();
            // ③将XML解析处理器分配给解析器
            // ④对文档进行解析，将每个事件发送给处理器。
            parser.parse(inStream, contentHandler);
            inStream.close();
            return contentHandler.getPersons();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //SAX类：DefaultHandler，它实现了ContentHandler接口。在实现的时候，只需要继承该类，重载相应的方法即可。
    public static class XMLContentHandler extends DefaultHandler {

        private VideoDetail currentPerson;
        private String tagName = null;//当前解析的元素标签

        public VideoDetail getPersons() {
            return currentPerson;
        }

        //接收文档开始的通知。当遇到文档的开头的时候，调用这个方法，可以在其中做一些预处理的工作。
        @Override
        public void startDocument() throws SAXException {
            LogUtils.i("SAXXmlContentHandler", "开始解析XML文件");
        }

        //接收元素开始的通知。当读到一个开始标签的时候，会触发这个方法。其中namespaceURI表示元素的命名空间；
        //localName表示元素的本地名称（不带前缀）；qName表示元素的限定名（带前缀）；atts 表示元素的属性集合
        @Override
        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
            LogUtils.e("SAXXmlContentHandler"+"读取标签"+localName);
            if (localName.equals("video")) {

            }
            this.tagName = localName;
        }

        //接收字符数据的通知。该方法用来处理在XML文件中读到的内容，第一个参数用于存放文件的内容，
        //后面两个参数是读到的字符串在这个数组中的起始位置和长度，使用new String(ch,start,length)就可以获取内容。
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            LogUtils.e("SAXXmlContentHandler", "根据tagName获取标签的内容");
            if (tagName != null) {
                String data = new String(ch, start, length);
//                if(tagName.equals("video")){
//                    currentPerson = new VideoDetail();
//                    currentPerson.setCriterion(atts.getValue("criterion"));
//                    currentPerson.setPassDemand(atts.getValue("passDemand"));
//                    currentPerson.setPassStrategy(atts.getValue("passStrategy"));
//                }else if(tagName.equals("age")){
//                    this.currentPerson.setAge(Short.parseShort(data));
//                }
            }
        }

        //接收文档的结尾的通知。在遇到结束标签的时候，调用这个方法。其中，uri表示元素的命名空间；
        //localName表示元素的本地名称（不带前缀）；name表示元素的限定名（带前缀）
        @Override
        public void endElement(String uri, String localName, String name) throws SAXException {
            LogUtils.e("SAXXmlContentHandler", "endElement");
            if (localName.equals("person")) {
//                persons.add(currentPerson);
                currentPerson = null;
            }

            this.tagName = null;
        }

    }

    public static String getValue(String scour,String key){
        int index = scour.indexOf(key);
        if(index != -1){
            LogUtils.i("valus index = "+index);
            int index2 = scour.indexOf("=\"",index);
            int index3 = scour.indexOf( "\"", index2+2);
            return scour.substring(index2+2, index3).replace("&lt;br/&gt;", "\n");
        }
        return null;
    }

    public static String getVideoDetailUrl(int id){
        return "http://dev.cheyooh.com/cheyooh_driving?channel=G003%25E8%2585%25BE%25E8%25AE%25AFv1.11.0&" +
                "id="+id+"&location_cityid=07001&m=videos_detail&tagversion=va&uid=34dfe1c832e24632b932079b0358d732&ver=1.11.0&checksign=4051814c9a10976627a860656fef2942";
    }

    public static void put(List<ExamRecord> tList, String key){
        if(tList == null || tList.size() == 0){
            return;
        }
        String json = new Gson().toJson(tList);
        ACache.get(StarterKitApp.appContext()).put(key, json);
    }

    public static List<ExamRecord> getAsList(String key){
        Gson gson = new Gson();
        Type type = new TypeToken<List<ExamRecord>>(){}.getType();
        return gson.fromJson(ACache.get(StarterKitApp.appContext()).getAsString(key), type);
    }


}
