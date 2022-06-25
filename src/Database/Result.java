package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Result implements AutoCloseable{

    private final PreparedStatement statement;
    private final ResultSet set;

    public Result(PreparedStatement statement, ResultSet set) {
        this.statement = statement;
        this.set = set;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return set;
    }

    @Override
    public void close(){
        try {

        }catch (Exception ignored){

        }
    }
}
