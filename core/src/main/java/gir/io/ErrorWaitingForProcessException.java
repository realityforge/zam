package gir.io;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import gir.GirException;

public class ErrorWaitingForProcessException
  extends GirException
{
  @Nonnull
  private final List<String> _command;

  public ErrorWaitingForProcessException( @Nonnull final List<String> command, @Nonnull final Throwable cause )
  {
    super( cause );
    _command = Objects.requireNonNull( command );
  }

  @Nonnull
  public List<String> getCommand()
  {
    return _command;
  }
}