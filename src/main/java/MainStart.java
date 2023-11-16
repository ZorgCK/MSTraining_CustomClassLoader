import one.microstream.storage.embedded.types.EmbeddedStorage;


public class MainStart
{
	public static void main(String[] args)
	{
		EmbeddedStorage.Foundation().onConnectionFoundation(cf ->
		{
			// cf.setClassLoaderProvider(typename -> Thread.currentThread().getContextClassLoader());
			
			cf.setClassLoaderProvider(typeName ->
			{
				if(typeName.startsWith("one.microstream.tenant1"))
				{
					return ClassLoader.getPlatformClassLoader();
				}
				
				if(typeName.startsWith("one.microstream.tenant2"))
				{
					return Thread.currentThread().getContextClassLoader();
				}
				
				return ClassLoader.getSystemClassLoader();
			});
			
		}).start();
	}
}
