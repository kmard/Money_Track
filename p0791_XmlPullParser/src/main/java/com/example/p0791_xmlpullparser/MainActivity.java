package com.example.p0791_xmlpullparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tmp = "";

        try {
            XmlPullParser xpp = prepareXpp();

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    //begin of the document
                    case XmlPullParser.START_DOCUMENT:
                        Log.i(TAG, "START_DOCUMENT");
                        break;
                    //begin tag
                    case XmlPullParser.START_TAG:
                        Log.i(TAG, "START_TAG : name = " + xpp.getName()
                                + ", depth = " + xpp.getDepth() + " , attrCount = "
                                + xpp.getAttributeCount());
                        tmp = "";
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                        }
                        if (!TextUtils.isEmpty(tmp))
                            Log.i(TAG, "Attributes : " + tmp);
                        break;
                    //finish tag
                    case XmlPullParser.END_TAG:
                        Log.i(TAG, "END_TAG : name = " + xpp.getName());
                        break;
                    //tag content
                    case XmlPullParser.TEXT:
                        Log.i(TAG, "text = " + xpp.getText());
                        break;
                    default:
                        break;
                }
                //next element
                xpp.next();
            }
            Log.i(TAG, "END_DOCUMENT");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data);
    }

//    //if xml receiving from anyway
//    XmlPullParser prepareXpp() throws XmlPullParserException {
//        // recieve factory
//        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//        //set support namespace (disable on default)
//        factory.setNamespaceAware(true);
//        //create parser
//        XmlPullParser xpp = factory.newPullParser();
//        //put on parser Reader
//        xpp.setInput(new StringReader(
//                "<data><phone><company>Samsung</company></phone></data>"));
//        return xpp;
//    }

}
