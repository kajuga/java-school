package afedorov.dao;

import afedorov.entities.Parameter;
import afedorov.entities.ValueOfParameter;

import java.util.List;

public interface ValueOfParameterDao {

    void add (ValueOfParameter valueOfParameter);
    void delete (ValueOfParameter valueOfParameter);
}
