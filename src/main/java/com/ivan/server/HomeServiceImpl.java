package com.ivan.server;

import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.api.exception.ExceptionCode;
import com.filenet.api.util.UserContext;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ivan.client.application.home.HomeService;
import javax.security.auth.Subject;
import javax.servlet.ServletException;

public class HomeServiceImpl extends RemoteServiceServlet implements HomeService {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void connectFileNet(String name) throws Exception {

            String uri = "http://172.19.215.15:9080/wsi/FNCEWS40MTOM/";
            String username = "fnadmin";
            String password = "o9p0[-]=";

            Connection conn = Factory.Connection.getConnection(uri);

            UserContext uc = UserContext.get();

            uc.pushSubject(
                    UserContext.createSubject(conn,username,password,null )
            );

            try
            {
                Domain domain = Factory.Domain.getInstance(conn, null);

                ObjectStore os = Factory.ObjectStore.fetchInstance(domain,
                        "System", null);

            }
            catch( EngineRuntimeException ex)
            {
                throw new Exception(ex.getExceptionCode().toString());
            }
            finally
            {
                uc.popSubject();
            }
        }
}