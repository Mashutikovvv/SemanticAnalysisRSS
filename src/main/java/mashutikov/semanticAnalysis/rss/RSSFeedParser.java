package mashutikov.semanticAnalysis.rss;

import mashutikov.semanticAnalysis.model.Feed;
import mashutikov.semanticAnalysis.model.FeedMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class RSSFeedParser {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
            // read the XML document
            String tagEndName = "";
            String tagName = "";
            String tagContent = "";
            Boolean istagEnd;
            List<FeedMessage> newsList = new ArrayList<>();
            while (streamReader.hasNext()) {
                istagEnd = false;
                switch (streamReader.getEventType()) {
                    case XMLStreamConstants.CHARACTERS:
                    case XMLStreamConstants.CDATA:
                        if(streamReader.getText().trim().length() > 0) {
                            tagContent = streamReader.getText();
                        }
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        tagName = streamReader.getLocalName();
                        tagContent = "";
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        tagEndName = streamReader.getLocalName();
                        istagEnd = true;
                        break;
                    default:
                        break;
                }
                if(ITEM.equals(tagName) && isFeedHeader) {
                        isFeedHeader = false;
                        feed = new Feed(title, link, description, language,
                                copyright, pubdate);

                }
                 if(istagEnd) {
                     switch (tagEndName) {
                         case TITLE:
                             title = tagContent;
                             break;
                         case DESCRIPTION:
                             description = tagContent;
                             break;
                         case LINK:
                             link = tagContent;
                             break;
                         case GUID:
                             guid = tagContent;
                             break;
                         case LANGUAGE:
                             language = tagContent;
                             break;
                         case AUTHOR:
                             author = tagContent;
                             break;
                         case PUB_DATE:
                             pubdate = tagContent;
                             break;
                         case COPYRIGHT:
                             copyright = tagContent;
                             break;
                     }
                     if (ITEM.equals(tagEndName)) {
                         FeedMessage message = new FeedMessage();
                         message.setAuthor(author);
                         message.setDescription(description);
                         message.setGuid(guid);
                         message.setLink(link);
                         message.setTitle(title);
                         newsList.add(message);
                     }
                 }
                streamReader.next();
            }
            feed.getMessages().addAll(newsList);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}