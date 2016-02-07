//-----------------------------------------------------------------------------
package dk.sunepoulsen.wrappers.json;

//-----------------------------------------------------------------------------
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.IOException;
import java.util.List;

//-----------------------------------------------------------------------------
/**
 * Provides static methods to encode and decode json strings to and from Java POJO's.
 */
public class Json {
    public static <T> T decode( String text, Class<T> valueType ) throws IOException {
        logger.entry( text, valueType );

        T result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return result = mapper.readValue( text, valueType );
        }
        finally {
            logger.exit( result );
        }
    }

    public static <T> List<T> decodeList( String text, Class<T> valueType ) throws IOException {
        logger.entry( text, valueType );

        List<T> result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            final CollectionType collectionType = mapper.getTypeFactory().constructCollectionType( List.class, valueType );

            return result = mapper.readValue( text, collectionType );
        }
        finally {
            logger.exit( result );
        }
    }

    public static String encode( Object value ) throws JsonProcessingException {
        logger.entry( value );

        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return result = mapper.writeValueAsString( value );
        }
        finally {
            logger.exit( result );
        }
    }

    public static String encodePretty( Object value ) throws JsonProcessingException {
        logger.entry( value );

        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            return result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString( value );
        }
        finally {
            logger.exit( result );
        }
    }

    //-------------------------------------------------------------------------
    //                  Private members
    //-------------------------------------------------------------------------

    private static XLogger logger = XLoggerFactory.getXLogger( Json.class );
}
