package io.mycat.net2.states;

import java.io.IOException;

import io.mycat.machine.State;
import io.mycat.machine.StateMachine;
import io.mycat.mysql.MySQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.mycat.backend.WriteCompleteListener;
import io.mycat.net2.Connection;

/**
 * 当前状态会
 *
 * @author yanjunli
 */
public class NewCmdState implements State {

    public static final NewCmdState INSTANCE = new NewCmdState();


    private NewCmdState() {
    }

    @Override
    public boolean handle(StateMachine context, Connection conn, Object attachment) throws IOException {
        MySQLConnection mySQLConnection = (MySQLConnection) conn;
        if (mySQLConnection.getProtocolStateMachine().getNextState() == null) {
            throw new IllegalStateException(conn + " has no nextState!");
        }
        ((Connection.NetworkStateMachine) context).getBuffer().compact();
        conn.getNetworkStateMachine().setNextState(ReadWaitingState.INSTANCE);
        return true;
    }

}
