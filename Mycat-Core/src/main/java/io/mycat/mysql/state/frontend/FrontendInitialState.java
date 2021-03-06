package io.mycat.mysql.state.frontend;

import io.mycat.front.MySQLFrontConnection;
import io.mycat.common.StateMachine;
import io.mycat.mysql.state.AbstractMysqlConnectionState;
import io.mycat.net2.Connection;

/**
 * 初始状态
 *
 * @author ynfeng
 */
public class FrontendInitialState extends AbstractMysqlConnectionState {
    public static final FrontendInitialState INSTANCE = new FrontendInitialState();

    private FrontendInitialState() {
    }

    /**
     * 前端连接在初始状态下不做任何动作，转到至连接中状态
     */
    @Override
    public boolean handle(StateMachine stateMachine, Connection connection, Object attachment) {
        MySQLFrontConnection mySQLFrontConnection = (MySQLFrontConnection) connection;
        mySQLFrontConnection.getProtocolStateMachine().setNextState(FrontendConnectingState.INSTANCE);
        return true;
    }
}
