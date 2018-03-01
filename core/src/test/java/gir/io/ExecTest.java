package gir.io;

import gir.AbstractGirTest;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ExecTest
  extends AbstractGirTest
{
  @Test
  public void cmd()
    throws Exception
  {
    final ProcessBuilder builder = new ProcessBuilder();
    Exec.cmd( builder, "echo", "hi" );

    final List<String> command = builder.command();
    assertEquals( command.size(), 2 );
    assertEquals( command.get( 0 ), "echo" );
    assertEquals( command.get( 1 ), "hi" );
  }

  @Test
  public void cmd_withNulls()
    throws Exception
  {
    final ProcessBuilder builder = new ProcessBuilder();
    Exec.cmd( builder, "git", "push", null, "origin" );

    final List<String> command = builder.command();
    assertEquals( command.size(), 3 );
    assertEquals( command.get( 0 ), "git" );
    assertEquals( command.get( 1 ), "push" );
    assertEquals( command.get( 2 ), "origin" );
  }
}