package org.flymine.objectstore.query;

import java.lang.reflect.Field;

import org.flymine.util.TypeUtil;

/**
 * Represents a field of a QueryClass that is a collection of objects of a single type
 *
 * @author Mark Woodbridge
 * @author Richard Smith
 */
public class QueryCollection
{
    private QueryClass qc;
    private String fieldName;
    private Class type;
    
    /**
     * Constructs a QueryCollection representing the specified field of a QueryClass
     *
     * @param qc the QueryClass
     * @param fieldName the name of the relevant field
     * @throws NullPointerException if the field name is null
     * @throws NoSuchFieldException if the field does not exist
     * @throws IllegalArgumentException if the field is not a collection
     */    
    public QueryCollection(QueryClass qc, String fieldName) 
        throws NullPointerException, NoSuchFieldException, IllegalArgumentException {
        if (fieldName == null) {
            throw new NullPointerException("Field name parameter is null");
        }
        Field field = TypeUtil.getField(qc.getType(), fieldName);
        if (field == null) {
            throw new NoSuchFieldException("Field " + fieldName + " not found in class "
                                           + qc.getType());
        }
        if (!java.util.Collection.class.isAssignableFrom(field.getType())) {
            throw new IllegalArgumentException("Field " + fieldName + " is not a collection type");
        }
        this.qc = qc;
        this.fieldName = fieldName;
        this.type = field.getType();
    }
    
    /**
     * Gets the QueryClass of which the collection is an member
     *
     * @return the QueryClass
     */    
    public QueryClass getQueryClass() {
        return qc;
    }

    /**
     * Gets the Java class of the elements of this QueryCollection
     *
     * @return the class name
     */    
    public Class getType() {
        return type;
    }
}
