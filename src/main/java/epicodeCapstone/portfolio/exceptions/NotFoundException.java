package epicodeCapstone.portfolio.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

    public NotFoundException(UUID id){super("L'id '" + id + "' non trovato");}

    public NotFoundException(String message){super(message);}
}
