Practical examples of Dagger2(dagger-android) in Android

Using Dagger.Android, you can easily set up the whole DI thing in your android app but when it comes to customising it for multi-module or dynamic-feature-module, it becomes very cumbersome to manage.

Good thing about the Dagger.Android is that you do not need to mention injections in Activities/Fragments but you still need to put AndroidInjection.inject(this) in your services, content providers and broadcast receivers.

Other good point about it is you can handle scoping in a better way by telling what all dependencies/dagger module will be used in a particular activity, like this

@ContributesAndroidInjector(modules = [ MainBindModule::class ]) abstract fun mainActivity(): MainActivity

Here MainBindModule will have the dependencies which will be used in MainActivity like Fragments, Implementation of data particular to the MainActivity

@Module public abstract class MainBindModule {

@ContributesAndroidInjector
abstract HomeFragment homeFragment();

@ContributesAndroidInjector
abstract MPINVerifyFragment customerMpin();

@Binds
abstract HomeRouter provideHomeRouter(HomeRouterImpl homeRouterImpl);

@Binds
abstract MainRouter provideMainRouter(MainRouterImpl mainRouter);
}
