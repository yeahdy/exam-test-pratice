package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Returns the group of libraries at com
     */
    public ComLibraryAccessors getCom() { return laccForComLibraryAccessors; }

    /**
     * Returns the group of libraries at io
     */
    public IoLibraryAccessors getIo() { return laccForIoLibraryAccessors; }

    /**
     * Returns the group of libraries at org
     */
    public OrgLibraryAccessors getOrg() { return laccForOrgLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComH2databaseLibraryAccessors laccForComH2databaseLibraryAccessors = new ComH2databaseLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.h2database
         */
        public ComH2databaseLibraryAccessors getH2database() { return laccForComH2databaseLibraryAccessors; }

    }

    public static class ComH2databaseLibraryAccessors extends SubDependencyFactory {

        public ComH2databaseLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for h2 (com.h2database:h2)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getH2() { return create("com.h2database.h2"); }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoNettyLibraryAccessors laccForIoNettyLibraryAccessors = new IoNettyLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.netty
         */
        public IoNettyLibraryAccessors getNetty() { return laccForIoNettyLibraryAccessors; }

    }

    public static class IoNettyLibraryAccessors extends SubDependencyFactory {
        private final IoNettyNettyLibraryAccessors laccForIoNettyNettyLibraryAccessors = new IoNettyNettyLibraryAccessors(owner);

        public IoNettyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.netty.netty
         */
        public IoNettyNettyLibraryAccessors getNetty() { return laccForIoNettyNettyLibraryAccessors; }

    }

    public static class IoNettyNettyLibraryAccessors extends SubDependencyFactory {
        private final IoNettyNettyResolverLibraryAccessors laccForIoNettyNettyResolverLibraryAccessors = new IoNettyNettyResolverLibraryAccessors(owner);

        public IoNettyNettyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.netty.netty.resolver
         */
        public IoNettyNettyResolverLibraryAccessors getResolver() { return laccForIoNettyNettyResolverLibraryAccessors; }

    }

    public static class IoNettyNettyResolverLibraryAccessors extends SubDependencyFactory {
        private final IoNettyNettyResolverDnsLibraryAccessors laccForIoNettyNettyResolverDnsLibraryAccessors = new IoNettyNettyResolverDnsLibraryAccessors(owner);

        public IoNettyNettyResolverLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.netty.netty.resolver.dns
         */
        public IoNettyNettyResolverDnsLibraryAccessors getDns() { return laccForIoNettyNettyResolverDnsLibraryAccessors; }

    }

    public static class IoNettyNettyResolverDnsLibraryAccessors extends SubDependencyFactory {
        private final IoNettyNettyResolverDnsNativeLibraryAccessors laccForIoNettyNettyResolverDnsNativeLibraryAccessors = new IoNettyNettyResolverDnsNativeLibraryAccessors(owner);

        public IoNettyNettyResolverDnsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.netty.netty.resolver.dns.native
         */
        public IoNettyNettyResolverDnsNativeLibraryAccessors getNative() { return laccForIoNettyNettyResolverDnsNativeLibraryAccessors; }

    }

    public static class IoNettyNettyResolverDnsNativeLibraryAccessors extends SubDependencyFactory {

        public IoNettyNettyResolverDnsNativeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for macos (io.netty:netty-resolver-dns-native-macos)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMacos() { return create("io.netty.netty.resolver.dns.native.macos"); }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgProjectlombokLibraryAccessors laccForOrgProjectlombokLibraryAccessors = new OrgProjectlombokLibraryAccessors(owner);
        private final OrgSpringframeworkLibraryAccessors laccForOrgSpringframeworkLibraryAccessors = new OrgSpringframeworkLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.projectlombok
         */
        public OrgProjectlombokLibraryAccessors getProjectlombok() { return laccForOrgProjectlombokLibraryAccessors; }

        /**
         * Returns the group of libraries at org.springframework
         */
        public OrgSpringframeworkLibraryAccessors getSpringframework() { return laccForOrgSpringframeworkLibraryAccessors; }

    }

    public static class OrgProjectlombokLibraryAccessors extends SubDependencyFactory {

        public OrgProjectlombokLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for lombok (org.projectlombok:lombok)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLombok() { return create("org.projectlombok.lombok"); }

    }

    public static class OrgSpringframeworkLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootLibraryAccessors laccForOrgSpringframeworkBootLibraryAccessors = new OrgSpringframeworkBootLibraryAccessors(owner);
        private final OrgSpringframeworkCloudLibraryAccessors laccForOrgSpringframeworkCloudLibraryAccessors = new OrgSpringframeworkCloudLibraryAccessors(owner);

        public OrgSpringframeworkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot
         */
        public OrgSpringframeworkBootLibraryAccessors getBoot() { return laccForOrgSpringframeworkBootLibraryAccessors; }

        /**
         * Returns the group of libraries at org.springframework.cloud
         */
        public OrgSpringframeworkCloudLibraryAccessors getCloud() { return laccForOrgSpringframeworkCloudLibraryAccessors; }

    }

    public static class OrgSpringframeworkBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringLibraryAccessors laccForOrgSpringframeworkBootSpringLibraryAccessors = new OrgSpringframeworkBootSpringLibraryAccessors(owner);

        public OrgSpringframeworkBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring
         */
        public OrgSpringframeworkBootSpringLibraryAccessors getSpring() { return laccForOrgSpringframeworkBootSpringLibraryAccessors; }

    }

    public static class OrgSpringframeworkBootSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootLibraryAccessors laccForOrgSpringframeworkBootSpringBootLibraryAccessors = new OrgSpringframeworkBootSpringBootLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot
         */
        public OrgSpringframeworkBootSpringBootLibraryAccessors getBoot() { return laccForOrgSpringframeworkBootSpringBootLibraryAccessors; }

    }

    public static class OrgSpringframeworkBootSpringBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot.starter
         */
        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors getStarter() { return laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors; }

    }

    public static class OrgSpringframeworkBootSpringBootStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for test (org.springframework.boot:spring-boot-starter-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() { return create("org.springframework.boot.spring.boot.starter.test"); }

            /**
             * Creates a dependency provider for web (org.springframework.boot:spring-boot-starter-web)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWeb() { return create("org.springframework.boot.spring.boot.starter.web"); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot.starter.data
         */
        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors getData() { return laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors; }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jpa (org.springframework.boot:spring-boot-starter-data-jpa)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJpa() { return create("org.springframework.boot.spring.boot.starter.data.jpa"); }

    }

    public static class OrgSpringframeworkCloudLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkCloudSpringLibraryAccessors laccForOrgSpringframeworkCloudSpringLibraryAccessors = new OrgSpringframeworkCloudSpringLibraryAccessors(owner);

        public OrgSpringframeworkCloudLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.cloud.spring
         */
        public OrgSpringframeworkCloudSpringLibraryAccessors getSpring() { return laccForOrgSpringframeworkCloudSpringLibraryAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkCloudSpringCloudLibraryAccessors laccForOrgSpringframeworkCloudSpringCloudLibraryAccessors = new OrgSpringframeworkCloudSpringCloudLibraryAccessors(owner);

        public OrgSpringframeworkCloudSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.cloud.spring.cloud
         */
        public OrgSpringframeworkCloudSpringCloudLibraryAccessors getCloud() { return laccForOrgSpringframeworkCloudSpringCloudLibraryAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkCloudSpringCloudStarterLibraryAccessors laccForOrgSpringframeworkCloudSpringCloudStarterLibraryAccessors = new OrgSpringframeworkCloudSpringCloudStarterLibraryAccessors(owner);

        public OrgSpringframeworkCloudSpringCloudLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.cloud.spring.cloud.starter
         */
        public OrgSpringframeworkCloudSpringCloudStarterLibraryAccessors getStarter() { return laccForOrgSpringframeworkCloudSpringCloudStarterLibraryAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors laccForOrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors = new OrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors(owner);

        public OrgSpringframeworkCloudSpringCloudStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bootstrap (org.springframework.cloud:spring-cloud-starter-bootstrap)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBootstrap() { return create("org.springframework.cloud.spring.cloud.starter.bootstrap"); }

            /**
             * Creates a dependency provider for config (org.springframework.cloud:spring-cloud-starter-config)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getConfig() { return create("org.springframework.cloud.spring.cloud.starter.config"); }

        /**
         * Returns the group of libraries at org.springframework.cloud.spring.cloud.starter.netflix
         */
        public OrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors getNetflix() { return laccForOrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors laccForOrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors = new OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors(owner);

        public OrgSpringframeworkCloudSpringCloudStarterNetflixLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.cloud.spring.cloud.starter.netflix.eureka
         */
        public OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors getEureka() { return laccForOrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for client (org.springframework.cloud:spring-cloud-starter-netflix-eureka-client)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getClient() { return create("org.springframework.cloud.spring.cloud.starter.netflix.eureka.client"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com
         */
        public ComVersionAccessors getCom() { return vaccForComVersionAccessors; }

        /**
         * Returns the group of versions at versions.io
         */
        public IoVersionAccessors getIo() { return vaccForIoVersionAccessors; }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() { return vaccForOrgVersionAccessors; }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComH2databaseVersionAccessors vaccForComH2databaseVersionAccessors = new ComH2databaseVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.h2database
         */
        public ComH2databaseVersionAccessors getH2database() { return vaccForComH2databaseVersionAccessors; }

    }

    public static class ComH2databaseVersionAccessors extends VersionFactory  {

        public ComH2databaseVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.h2database.h2 (2.2.224)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getH2() { return getVersion("com.h2database.h2"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoNettyVersionAccessors vaccForIoNettyVersionAccessors = new IoNettyVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.netty
         */
        public IoNettyVersionAccessors getNetty() { return vaccForIoNettyVersionAccessors; }

    }

    public static class IoNettyVersionAccessors extends VersionFactory  {

        private final IoNettyNettyVersionAccessors vaccForIoNettyNettyVersionAccessors = new IoNettyNettyVersionAccessors(providers, config);
        public IoNettyVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.netty.netty
         */
        public IoNettyNettyVersionAccessors getNetty() { return vaccForIoNettyNettyVersionAccessors; }

    }

    public static class IoNettyNettyVersionAccessors extends VersionFactory  {

        private final IoNettyNettyResolverVersionAccessors vaccForIoNettyNettyResolverVersionAccessors = new IoNettyNettyResolverVersionAccessors(providers, config);
        public IoNettyNettyVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.netty.netty.resolver
         */
        public IoNettyNettyResolverVersionAccessors getResolver() { return vaccForIoNettyNettyResolverVersionAccessors; }

    }

    public static class IoNettyNettyResolverVersionAccessors extends VersionFactory  {

        private final IoNettyNettyResolverDnsVersionAccessors vaccForIoNettyNettyResolverDnsVersionAccessors = new IoNettyNettyResolverDnsVersionAccessors(providers, config);
        public IoNettyNettyResolverVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.netty.netty.resolver.dns
         */
        public IoNettyNettyResolverDnsVersionAccessors getDns() { return vaccForIoNettyNettyResolverDnsVersionAccessors; }

    }

    public static class IoNettyNettyResolverDnsVersionAccessors extends VersionFactory  {

        private final IoNettyNettyResolverDnsNativeVersionAccessors vaccForIoNettyNettyResolverDnsNativeVersionAccessors = new IoNettyNettyResolverDnsNativeVersionAccessors(providers, config);
        public IoNettyNettyResolverDnsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.netty.netty.resolver.dns.native
         */
        public IoNettyNettyResolverDnsNativeVersionAccessors getNative() { return vaccForIoNettyNettyResolverDnsNativeVersionAccessors; }

    }

    public static class IoNettyNettyResolverDnsNativeVersionAccessors extends VersionFactory  {

        public IoNettyNettyResolverDnsNativeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: io.netty.netty.resolver.dns.native.macos (4.1.85.Final)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMacos() { return getVersion("io.netty.netty.resolver.dns.native.macos"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgProjectlombokVersionAccessors vaccForOrgProjectlombokVersionAccessors = new OrgProjectlombokVersionAccessors(providers, config);
        private final OrgSpringframeworkVersionAccessors vaccForOrgSpringframeworkVersionAccessors = new OrgSpringframeworkVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.projectlombok
         */
        public OrgProjectlombokVersionAccessors getProjectlombok() { return vaccForOrgProjectlombokVersionAccessors; }

        /**
         * Returns the group of versions at versions.org.springframework
         */
        public OrgSpringframeworkVersionAccessors getSpringframework() { return vaccForOrgSpringframeworkVersionAccessors; }

    }

    public static class OrgProjectlombokVersionAccessors extends VersionFactory  {

        public OrgProjectlombokVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.projectlombok.lombok (1.18.30)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLombok() { return getVersion("org.projectlombok.lombok"); }

    }

    public static class OrgSpringframeworkVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootVersionAccessors vaccForOrgSpringframeworkBootVersionAccessors = new OrgSpringframeworkBootVersionAccessors(providers, config);
        private final OrgSpringframeworkCloudVersionAccessors vaccForOrgSpringframeworkCloudVersionAccessors = new OrgSpringframeworkCloudVersionAccessors(providers, config);
        public OrgSpringframeworkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot
         */
        public OrgSpringframeworkBootVersionAccessors getBoot() { return vaccForOrgSpringframeworkBootVersionAccessors; }

        /**
         * Returns the group of versions at versions.org.springframework.cloud
         */
        public OrgSpringframeworkCloudVersionAccessors getCloud() { return vaccForOrgSpringframeworkCloudVersionAccessors; }

    }

    public static class OrgSpringframeworkBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringVersionAccessors vaccForOrgSpringframeworkBootSpringVersionAccessors = new OrgSpringframeworkBootSpringVersionAccessors(providers, config);
        public OrgSpringframeworkBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring
         */
        public OrgSpringframeworkBootSpringVersionAccessors getSpring() { return vaccForOrgSpringframeworkBootSpringVersionAccessors; }

    }

    public static class OrgSpringframeworkBootSpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootVersionAccessors vaccForOrgSpringframeworkBootSpringBootVersionAccessors = new OrgSpringframeworkBootSpringBootVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot
         */
        public OrgSpringframeworkBootSpringBootVersionAccessors getBoot() { return vaccForOrgSpringframeworkBootSpringBootVersionAccessors; }

    }

    public static class OrgSpringframeworkBootSpringBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors = new OrgSpringframeworkBootSpringBootStarterVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot.starter
         */
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors getStarter() { return vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors; }

    }

    public static class OrgSpringframeworkBootSpringBootStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterDataVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors = new OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.test (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTest() { return getVersion("org.springframework.boot.spring.boot.starter.test"); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.web (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getWeb() { return getVersion("org.springframework.boot.spring.boot.starter.web"); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot.starter.data
         */
        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors getData() { return vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors; }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.data.jpa (3.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJpa() { return getVersion("org.springframework.boot.spring.boot.starter.data.jpa"); }

    }

    public static class OrgSpringframeworkCloudVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkCloudSpringVersionAccessors vaccForOrgSpringframeworkCloudSpringVersionAccessors = new OrgSpringframeworkCloudSpringVersionAccessors(providers, config);
        public OrgSpringframeworkCloudVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.cloud.spring
         */
        public OrgSpringframeworkCloudSpringVersionAccessors getSpring() { return vaccForOrgSpringframeworkCloudSpringVersionAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkCloudSpringCloudVersionAccessors vaccForOrgSpringframeworkCloudSpringCloudVersionAccessors = new OrgSpringframeworkCloudSpringCloudVersionAccessors(providers, config);
        public OrgSpringframeworkCloudSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.cloud.spring.cloud
         */
        public OrgSpringframeworkCloudSpringCloudVersionAccessors getCloud() { return vaccForOrgSpringframeworkCloudSpringCloudVersionAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkCloudSpringCloudStarterVersionAccessors vaccForOrgSpringframeworkCloudSpringCloudStarterVersionAccessors = new OrgSpringframeworkCloudSpringCloudStarterVersionAccessors(providers, config);
        public OrgSpringframeworkCloudSpringCloudVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.cloud.spring.cloud.starter
         */
        public OrgSpringframeworkCloudSpringCloudStarterVersionAccessors getStarter() { return vaccForOrgSpringframeworkCloudSpringCloudStarterVersionAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors vaccForOrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors = new OrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors(providers, config);
        public OrgSpringframeworkCloudSpringCloudStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.cloud.spring.cloud.starter.bootstrap (4.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBootstrap() { return getVersion("org.springframework.cloud.spring.cloud.starter.bootstrap"); }

            /**
             * Returns the version associated to this alias: org.springframework.cloud.spring.cloud.starter.config (4.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConfig() { return getVersion("org.springframework.cloud.spring.cloud.starter.config"); }

        /**
         * Returns the group of versions at versions.org.springframework.cloud.spring.cloud.starter.netflix
         */
        public OrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors getNetflix() { return vaccForOrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors vaccForOrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors = new OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors(providers, config);
        public OrgSpringframeworkCloudSpringCloudStarterNetflixVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.cloud.spring.cloud.starter.netflix.eureka
         */
        public OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors getEureka() { return vaccForOrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors; }

    }

    public static class OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkCloudSpringCloudStarterNetflixEurekaVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.cloud.spring.cloud.starter.netflix.eureka.client (4.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getClient() { return getVersion("org.springframework.cloud.spring.cloud.starter.netflix.eureka.client"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
