package ru.mentee.power.parsing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;

public class XmlDataConverter {

    public String toXml(ConfigurationData data) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("configuration");
            doc.appendChild(root);

            Element serverUrl = doc.createElement("serverUrl");
            serverUrl.setTextContent(data.getServerUrl() == null ? "" : data.getServerUrl());
            root.appendChild(serverUrl);

            Element port = doc.createElement("port");
            port.setTextContent(String.valueOf(data.getPort()));
            root.appendChild(port);

            Element featureFlags = doc.createElement("featureFlags");
            if (data.getFeatureFlags() != null) {
                for (String flag : data.getFeatureFlags()) {
                    Element flagEl = doc.createElement("flag");
                    flagEl.setTextContent(flag);
                    featureFlags.appendChild(flagEl);
                }
            }
            root.appendChild(featureFlags);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка сериализации в XML", e);
        }
    }

    public ConfigurationData fromXml(String xml) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            String serverUrl = getTextContentByTag(root, "serverUrl");
            String portText = getTextContentByTag(root, "port");
            int port = 0;
            if (portText != null && !portText.isEmpty()) {
                port = Integer.parseInt(portText.trim());
            }

            List<String> flags = new ArrayList<>();
            Element featureFlagsEl = (Element) root.getElementsByTagName("featureFlags").item(0);
            if (featureFlagsEl != null) {
                org.w3c.dom.NodeList flagNodes = featureFlagsEl.getElementsByTagName("flag");
                for (int i = 0; i < flagNodes.getLength(); i++) {
                    String txt = flagNodes.item(i).getTextContent();
                    if (txt != null && !txt.isEmpty()) flags.add(txt);
                }
            }

            return new ConfigurationData(serverUrl, port, flags);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка десериализации из XML", e);
        }
    }

    private String getTextContentByTag(Element parent, String tag) {
        if (parent.getElementsByTagName(tag).getLength() == 0) return null;
        return parent.getElementsByTagName(tag).item(0).getTextContent();
    }
}
