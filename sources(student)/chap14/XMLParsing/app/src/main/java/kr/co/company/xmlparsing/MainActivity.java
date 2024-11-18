package kr.co.company.xmlparsing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    Document doc = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = (TextView) findViewById(R.id.textView1);
    }

    public void onClick(View view) {
        Thread downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doc = getXML("https://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
                    textview.post(new Runnable() {
                        @Override
                        public void run() {
                            postproc(doc);
                            Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Log.d("Background Task", e.toString());
                }
            }
        });
        downloadThread.start();
    }

    private Document getXML(String urla) {
        try {
            URL url = new URL(urla);
            DocumentBuilderFactory dbf = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder db;
            db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

        } catch (Exception e) {
            //Toast.makeText(getBaseContext(), "Parsing Error",
            //        Toast.LENGTH_SHORT).show();
        }
        return doc;
    }

    protected void postproc(Document doc) {
        String s = "";
        NodeList nodeList = doc.getElementsByTagName("data");

        for (int i = 0; i < nodeList.getLength(); i++) {
            s += "" + i + ": 날씨 정보: ";
            Node node = nodeList.item(i);
            Element fstElmnt = (Element) node;

            NodeList nameList = fstElmnt.getElementsByTagName("temp");
            Element nameElement = (Element) nameList.item(0);
            nameList = nameElement.getChildNodes();

            s += "온도 = " + ((Node) nameList.item(0)).getNodeValue() + " ,";


            NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
            Element websiteElement = (Element) websiteList.item(0);
            websiteList = websiteElement.getChildNodes();
            s += "날씨 = " + ((Node) websiteList.item(0)).getNodeValue()
                    + "\n";
        }
        textview.setText(s);
    }
}
