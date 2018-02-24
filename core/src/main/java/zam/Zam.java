package zam;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Zam
{
  private static ZamContext c_context = new ZamContext();

  public static ZamContext context()
  {
    return c_context;
  }

  public static void main( String[] args )
    throws Exception
  {
    final Path workingDirectory = Paths.get( "/Users/peter/Code/zam" );
    IoUtil.inDirectory( workingDirectory, () -> {
      Git.clone( "https://github.com/realityforge/antix.git", "antix" );
      IoUtil.inDirectory( workingDirectory.resolve( "antix" ), () -> {
        Git.fetch();
        Git.resetBranch();
        Git.checkout();
        Git.pull();
      } );
    } );

    Zam.context().close();
  }
}
