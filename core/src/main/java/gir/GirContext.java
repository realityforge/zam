package gir;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;

/**
 * Context associated with a particular Gir session.
 */
public final class GirContext
{
  private final ExecutorService _executorService = Executors.newCachedThreadPool();
  private boolean _closed;
  @Nonnull
  private Messenger _messenger = new StandardMessenger( StandardMessenger.INFO );

  /**
   * Return the messenger associated with the context.
   *
   * @return the messenger associated with the context.
   */
  @Nonnull
  public Messenger getMessenger()
  {
    return _messenger;
  }

  /**
   * Set the messenger associated with the context.
   *
   * @param messenger the messenger.
   */
  public void setMessenger( @Nonnull final Messenger messenger )
  {
    _messenger = Objects.requireNonNull( messenger );
  }

  /**
   * Schedule an action to run asynchronously
   *
   * @param <V>    the return type of action.
   * @param action the action to schedule.
   * @return the future where the result is returned.
   */
  public <V> Future<V> run( @Nonnull final Callable<V> action )
  {
    if ( _closed )
    {
      throw new GirException( "GirContext.run() invoked on closed context" );
    }
    return _executorService.submit( action );
  }

  /**
   * Schedule an action to run asynchronously
   *
   * @param action the action to schedule.
   * @return the future that resolves when the action completes.
   */
  public Future<?> run( @Nonnull final Runnable action )
  {
    if ( _closed )
    {
      throw new GirException( "GirContext.run() invoked on closed context" );
    }
    return _executorService.submit( action );
  }

  /**
   * Return true if context has been closed.
   *
   * @return true if context has been closed.
   */
  boolean isClosed()
  {
    return _closed;
  }

  /**
   * Close the context.
   * This will wait till any submitted tasks have completed before returning.
   */
  void close()
  {
    if ( _closed )
    {
      throw new GirException( "GirContext.close() invoked on closed context" );
    }
    _closed = true;
    _executorService.shutdown();
  }

  @Nonnull
  ExecutorService getExecutorService()
  {
    return _executorService;
  }
}
