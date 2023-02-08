package support;

import web.RequestHandler;
import web.controller.*;
import web.infra.MemorySessionManager;
import web.infra.SessionManager;
import web.validator.LoginValidator;

import java.net.Socket;

public class RequestHandlerFactory {

    private final static SessionManager sessionManager = new MemorySessionManager();
    private final static LoginValidator loginValidator = new LoginValidator(sessionManager);
    private final static HandlerMapping handlerMapping = HandlerMapping.of(
            new DefaultController(),
            new PostSignInController(),
            new PostLoginController(() -> "UUID", sessionManager),
            new GetUserListController(loginValidator),
            new GetResourceController(loginValidator)
    );
    public static RequestHandler create(Socket connectionSocket) {
        return new RequestHandler(connectionSocket, handlerMapping);
    }

}
