package afedorov.dao.impl.inmemory;

import afedorov.dao.ValueOfParameterDao;
import afedorov.entities.ValueOfParameter;
import java.util.HashSet;
import java.util.Set;

public class ValueOfParameterDaoImpl implements ValueOfParameterDao {
    private Set<ValueOfParameter> valueOfParameters = new HashSet<>();

    @Override
    public void add(ValueOfParameter valueOfParameter) {
        if (valueOfParameter != null) {
            valueOfParameters.add(valueOfParameter);
        }
    }

    @Override
    public void delete(ValueOfParameter valueOfParameter) {
        if (valueOfParameter != null);
        try {
            valueOfParameters.remove(valueOfParameter);
        } catch (RuntimeException e) {
            System.out.println("Missing parameter");
        }
    }
}
