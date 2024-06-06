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
    private final JavaxLibraryAccessors laccForJavaxLibraryAccessors = new JavaxLibraryAccessors(owner);
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
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Returns the group of libraries at io
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Returns the group of libraries at javax
     */
    public JavaxLibraryAccessors getJavax() {
        return laccForJavaxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at org
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google
         */
        public ComGoogleLibraryAccessors getGoogle() {
            return laccForComGoogleLibraryAccessors;
        }

    }

    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeLibraryAccessors laccForComGoogleCodeLibraryAccessors = new ComGoogleCodeLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.code
         */
        public ComGoogleCodeLibraryAccessors getCode() {
            return laccForComGoogleCodeLibraryAccessors;
        }

    }

    public static class ComGoogleCodeLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeGsonLibraryAccessors laccForComGoogleCodeGsonLibraryAccessors = new ComGoogleCodeGsonLibraryAccessors(owner);

        public ComGoogleCodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.code.gson
         */
        public ComGoogleCodeGsonLibraryAccessors getGson() {
            return laccForComGoogleCodeGsonLibraryAccessors;
        }

    }

    public static class ComGoogleCodeGsonLibraryAccessors extends SubDependencyFactory {

        public ComGoogleCodeGsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for gson (com.google.code.gson:gson)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGson() {
                return create("com.google.code.gson.gson");
        }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoJsonwebtokenLibraryAccessors laccForIoJsonwebtokenLibraryAccessors = new IoJsonwebtokenLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at io.jsonwebtoken
         */
        public IoJsonwebtokenLibraryAccessors getJsonwebtoken() {
            return laccForIoJsonwebtokenLibraryAccessors;
        }

    }

    public static class IoJsonwebtokenLibraryAccessors extends SubDependencyFactory {

        public IoJsonwebtokenLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jjwt (io.jsonwebtoken:jjwt)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJjwt() {
                return create("io.jsonwebtoken.jjwt");
        }

    }

    public static class JavaxLibraryAccessors extends SubDependencyFactory {
        private final JavaxXmlLibraryAccessors laccForJavaxXmlLibraryAccessors = new JavaxXmlLibraryAccessors(owner);

        public JavaxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.xml
         */
        public JavaxXmlLibraryAccessors getXml() {
            return laccForJavaxXmlLibraryAccessors;
        }

    }

    public static class JavaxXmlLibraryAccessors extends SubDependencyFactory {
        private final JavaxXmlBindLibraryAccessors laccForJavaxXmlBindLibraryAccessors = new JavaxXmlBindLibraryAccessors(owner);

        public JavaxXmlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.xml.bind
         */
        public JavaxXmlBindLibraryAccessors getBind() {
            return laccForJavaxXmlBindLibraryAccessors;
        }

    }

    public static class JavaxXmlBindLibraryAccessors extends SubDependencyFactory {
        private final JavaxXmlBindJaxbLibraryAccessors laccForJavaxXmlBindJaxbLibraryAccessors = new JavaxXmlBindJaxbLibraryAccessors(owner);

        public JavaxXmlBindLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at javax.xml.bind.jaxb
         */
        public JavaxXmlBindJaxbLibraryAccessors getJaxb() {
            return laccForJavaxXmlBindJaxbLibraryAccessors;
        }

    }

    public static class JavaxXmlBindJaxbLibraryAccessors extends SubDependencyFactory {

        public JavaxXmlBindJaxbLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for api (javax.xml.bind:jaxb-api)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getApi() {
                return create("javax.xml.bind.jaxb.api");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgPostgresqlLibraryAccessors laccForOrgPostgresqlLibraryAccessors = new OrgPostgresqlLibraryAccessors(owner);
        private final OrgProjectlombokLibraryAccessors laccForOrgProjectlombokLibraryAccessors = new OrgProjectlombokLibraryAccessors(owner);
        private final OrgSpringdocLibraryAccessors laccForOrgSpringdocLibraryAccessors = new OrgSpringdocLibraryAccessors(owner);
        private final OrgSpringframeworkLibraryAccessors laccForOrgSpringframeworkLibraryAccessors = new OrgSpringframeworkLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.postgresql
         */
        public OrgPostgresqlLibraryAccessors getPostgresql() {
            return laccForOrgPostgresqlLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.projectlombok
         */
        public OrgProjectlombokLibraryAccessors getProjectlombok() {
            return laccForOrgProjectlombokLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.springdoc
         */
        public OrgSpringdocLibraryAccessors getSpringdoc() {
            return laccForOrgSpringdocLibraryAccessors;
        }

        /**
         * Returns the group of libraries at org.springframework
         */
        public OrgSpringframeworkLibraryAccessors getSpringframework() {
            return laccForOrgSpringframeworkLibraryAccessors;
        }

    }

    public static class OrgPostgresqlLibraryAccessors extends SubDependencyFactory {

        public OrgPostgresqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for postgresql (org.postgresql:postgresql)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPostgresql() {
                return create("org.postgresql.postgresql");
        }

    }

    public static class OrgProjectlombokLibraryAccessors extends SubDependencyFactory {

        public OrgProjectlombokLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for lombok (org.projectlombok:lombok)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLombok() {
                return create("org.projectlombok.lombok");
        }

    }

    public static class OrgSpringdocLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocLibraryAccessors laccForOrgSpringdocSpringdocLibraryAccessors = new OrgSpringdocSpringdocLibraryAccessors(owner);

        public OrgSpringdocLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springdoc.springdoc
         */
        public OrgSpringdocSpringdocLibraryAccessors getSpringdoc() {
            return laccForOrgSpringdocSpringdocLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocOpenapiLibraryAccessors laccForOrgSpringdocSpringdocOpenapiLibraryAccessors = new OrgSpringdocSpringdocOpenapiLibraryAccessors(owner);

        public OrgSpringdocSpringdocLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springdoc.springdoc.openapi
         */
        public OrgSpringdocSpringdocOpenapiLibraryAccessors getOpenapi() {
            return laccForOrgSpringdocSpringdocOpenapiLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocOpenapiStarterLibraryAccessors laccForOrgSpringdocSpringdocOpenapiStarterLibraryAccessors = new OrgSpringdocSpringdocOpenapiStarterLibraryAccessors(owner);

        public OrgSpringdocSpringdocOpenapiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springdoc.springdoc.openapi.starter
         */
        public OrgSpringdocSpringdocOpenapiStarterLibraryAccessors getStarter() {
            return laccForOrgSpringdocSpringdocOpenapiStarterLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors laccForOrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors = new OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors(owner);

        public OrgSpringdocSpringdocOpenapiStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springdoc.springdoc.openapi.starter.webmvc
         */
        public OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors getWebmvc() {
            return laccForOrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors extends SubDependencyFactory {

        public OrgSpringdocSpringdocOpenapiStarterWebmvcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ui (org.springdoc:springdoc-openapi-starter-webmvc-ui)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUi() {
                return create("org.springdoc.springdoc.openapi.starter.webmvc.ui");
        }

    }

    public static class OrgSpringframeworkLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootLibraryAccessors laccForOrgSpringframeworkBootLibraryAccessors = new OrgSpringframeworkBootLibraryAccessors(owner);

        public OrgSpringframeworkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot
         */
        public OrgSpringframeworkBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringLibraryAccessors laccForOrgSpringframeworkBootSpringLibraryAccessors = new OrgSpringframeworkBootSpringLibraryAccessors(owner);

        public OrgSpringframeworkBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring
         */
        public OrgSpringframeworkBootSpringLibraryAccessors getSpring() {
            return laccForOrgSpringframeworkBootSpringLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootLibraryAccessors laccForOrgSpringframeworkBootSpringBootLibraryAccessors = new OrgSpringframeworkBootSpringBootLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot
         */
        public OrgSpringframeworkBootSpringBootLibraryAccessors getBoot() {
            return laccForOrgSpringframeworkBootSpringBootLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot.starter
         */
        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors getStarter() {
            return laccForOrgSpringframeworkBootSpringBootStarterLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterLibraryAccessors extends SubDependencyFactory {
        private final OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors = new OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(owner);

        public OrgSpringframeworkBootSpringBootStarterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for security (org.springframework.boot:spring-boot-starter-security)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSecurity() {
                return create("org.springframework.boot.spring.boot.starter.security");
        }

            /**
             * Creates a dependency provider for validation (org.springframework.boot:spring-boot-starter-validation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getValidation() {
                return create("org.springframework.boot.spring.boot.starter.validation");
        }

            /**
             * Creates a dependency provider for web (org.springframework.boot:spring-boot-starter-web)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getWeb() {
                return create("org.springframework.boot.spring.boot.starter.web");
        }

        /**
         * Returns the group of libraries at org.springframework.boot.spring.boot.starter.data
         */
        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors getData() {
            return laccForOrgSpringframeworkBootSpringBootStarterDataLibraryAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors extends SubDependencyFactory {

        public OrgSpringframeworkBootSpringBootStarterDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jpa (org.springframework.boot:spring-boot-starter-data-jpa)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJpa() {
                return create("org.springframework.boot.spring.boot.starter.data.jpa");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final JavaxVersionAccessors vaccForJavaxVersionAccessors = new JavaxVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.io
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.javax
         */
        public JavaxVersionAccessors getJavax() {
            return vaccForJavaxVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComGoogleVersionAccessors vaccForComGoogleVersionAccessors = new ComGoogleVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.google
         */
        public ComGoogleVersionAccessors getGoogle() {
            return vaccForComGoogleVersionAccessors;
        }

    }

    public static class ComGoogleVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeVersionAccessors vaccForComGoogleCodeVersionAccessors = new ComGoogleCodeVersionAccessors(providers, config);
        public ComGoogleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.google.code
         */
        public ComGoogleCodeVersionAccessors getCode() {
            return vaccForComGoogleCodeVersionAccessors;
        }

    }

    public static class ComGoogleCodeVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeGsonVersionAccessors vaccForComGoogleCodeGsonVersionAccessors = new ComGoogleCodeGsonVersionAccessors(providers, config);
        public ComGoogleCodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.com.google.code.gson
         */
        public ComGoogleCodeGsonVersionAccessors getGson() {
            return vaccForComGoogleCodeGsonVersionAccessors;
        }

    }

    public static class ComGoogleCodeGsonVersionAccessors extends VersionFactory  {

        public ComGoogleCodeGsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: com.google.code.gson.gson (2.9.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGson() { return getVersion("com.google.code.gson.gson"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoJsonwebtokenVersionAccessors vaccForIoJsonwebtokenVersionAccessors = new IoJsonwebtokenVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.io.jsonwebtoken
         */
        public IoJsonwebtokenVersionAccessors getJsonwebtoken() {
            return vaccForIoJsonwebtokenVersionAccessors;
        }

    }

    public static class IoJsonwebtokenVersionAccessors extends VersionFactory  {

        public IoJsonwebtokenVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: io.jsonwebtoken.jjwt (0.9.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJjwt() { return getVersion("io.jsonwebtoken.jjwt"); }

    }

    public static class JavaxVersionAccessors extends VersionFactory  {

        private final JavaxXmlVersionAccessors vaccForJavaxXmlVersionAccessors = new JavaxXmlVersionAccessors(providers, config);
        public JavaxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.xml
         */
        public JavaxXmlVersionAccessors getXml() {
            return vaccForJavaxXmlVersionAccessors;
        }

    }

    public static class JavaxXmlVersionAccessors extends VersionFactory  {

        private final JavaxXmlBindVersionAccessors vaccForJavaxXmlBindVersionAccessors = new JavaxXmlBindVersionAccessors(providers, config);
        public JavaxXmlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.xml.bind
         */
        public JavaxXmlBindVersionAccessors getBind() {
            return vaccForJavaxXmlBindVersionAccessors;
        }

    }

    public static class JavaxXmlBindVersionAccessors extends VersionFactory  {

        private final JavaxXmlBindJaxbVersionAccessors vaccForJavaxXmlBindJaxbVersionAccessors = new JavaxXmlBindJaxbVersionAccessors(providers, config);
        public JavaxXmlBindVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.javax.xml.bind.jaxb
         */
        public JavaxXmlBindJaxbVersionAccessors getJaxb() {
            return vaccForJavaxXmlBindJaxbVersionAccessors;
        }

    }

    public static class JavaxXmlBindJaxbVersionAccessors extends VersionFactory  {

        public JavaxXmlBindJaxbVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: javax.xml.bind.jaxb.api (2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getApi() { return getVersion("javax.xml.bind.jaxb.api"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgPostgresqlVersionAccessors vaccForOrgPostgresqlVersionAccessors = new OrgPostgresqlVersionAccessors(providers, config);
        private final OrgProjectlombokVersionAccessors vaccForOrgProjectlombokVersionAccessors = new OrgProjectlombokVersionAccessors(providers, config);
        private final OrgSpringdocVersionAccessors vaccForOrgSpringdocVersionAccessors = new OrgSpringdocVersionAccessors(providers, config);
        private final OrgSpringframeworkVersionAccessors vaccForOrgSpringframeworkVersionAccessors = new OrgSpringframeworkVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.postgresql
         */
        public OrgPostgresqlVersionAccessors getPostgresql() {
            return vaccForOrgPostgresqlVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.projectlombok
         */
        public OrgProjectlombokVersionAccessors getProjectlombok() {
            return vaccForOrgProjectlombokVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.springdoc
         */
        public OrgSpringdocVersionAccessors getSpringdoc() {
            return vaccForOrgSpringdocVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.org.springframework
         */
        public OrgSpringframeworkVersionAccessors getSpringframework() {
            return vaccForOrgSpringframeworkVersionAccessors;
        }

    }

    public static class OrgPostgresqlVersionAccessors extends VersionFactory  {

        public OrgPostgresqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.postgresql.postgresql (42.6.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPostgresql() { return getVersion("org.postgresql.postgresql"); }

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

    public static class OrgSpringdocVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocVersionAccessors vaccForOrgSpringdocSpringdocVersionAccessors = new OrgSpringdocSpringdocVersionAccessors(providers, config);
        public OrgSpringdocVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springdoc.springdoc
         */
        public OrgSpringdocSpringdocVersionAccessors getSpringdoc() {
            return vaccForOrgSpringdocSpringdocVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocOpenapiVersionAccessors vaccForOrgSpringdocSpringdocOpenapiVersionAccessors = new OrgSpringdocSpringdocOpenapiVersionAccessors(providers, config);
        public OrgSpringdocSpringdocVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springdoc.springdoc.openapi
         */
        public OrgSpringdocSpringdocOpenapiVersionAccessors getOpenapi() {
            return vaccForOrgSpringdocSpringdocOpenapiVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocOpenapiStarterVersionAccessors vaccForOrgSpringdocSpringdocOpenapiStarterVersionAccessors = new OrgSpringdocSpringdocOpenapiStarterVersionAccessors(providers, config);
        public OrgSpringdocSpringdocOpenapiVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springdoc.springdoc.openapi.starter
         */
        public OrgSpringdocSpringdocOpenapiStarterVersionAccessors getStarter() {
            return vaccForOrgSpringdocSpringdocOpenapiStarterVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors vaccForOrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors = new OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors(providers, config);
        public OrgSpringdocSpringdocOpenapiStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springdoc.springdoc.openapi.starter.webmvc
         */
        public OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors getWebmvc() {
            return vaccForOrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors;
        }

    }

    public static class OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors extends VersionFactory  {

        public OrgSpringdocSpringdocOpenapiStarterWebmvcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springdoc.springdoc.openapi.starter.webmvc.ui (2.0.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getUi() { return getVersion("org.springdoc.springdoc.openapi.starter.webmvc.ui"); }

    }

    public static class OrgSpringframeworkVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootVersionAccessors vaccForOrgSpringframeworkBootVersionAccessors = new OrgSpringframeworkBootVersionAccessors(providers, config);
        public OrgSpringframeworkVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot
         */
        public OrgSpringframeworkBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringVersionAccessors vaccForOrgSpringframeworkBootSpringVersionAccessors = new OrgSpringframeworkBootSpringVersionAccessors(providers, config);
        public OrgSpringframeworkBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring
         */
        public OrgSpringframeworkBootSpringVersionAccessors getSpring() {
            return vaccForOrgSpringframeworkBootSpringVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootVersionAccessors vaccForOrgSpringframeworkBootSpringBootVersionAccessors = new OrgSpringframeworkBootSpringBootVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot
         */
        public OrgSpringframeworkBootSpringBootVersionAccessors getBoot() {
            return vaccForOrgSpringframeworkBootSpringBootVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors = new OrgSpringframeworkBootSpringBootStarterVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot.starter
         */
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors getStarter() {
            return vaccForOrgSpringframeworkBootSpringBootStarterVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterVersionAccessors extends VersionFactory  {

        private final OrgSpringframeworkBootSpringBootStarterDataVersionAccessors vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors = new OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(providers, config);
        public OrgSpringframeworkBootSpringBootStarterVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.security (3.2.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSecurity() { return getVersion("org.springframework.boot.spring.boot.starter.security"); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.validation (3.2.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getValidation() { return getVersion("org.springframework.boot.spring.boot.starter.validation"); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.web (3.2.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getWeb() { return getVersion("org.springframework.boot.spring.boot.starter.web"); }

        /**
         * Returns the group of versions at versions.org.springframework.boot.spring.boot.starter.data
         */
        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors getData() {
            return vaccForOrgSpringframeworkBootSpringBootStarterDataVersionAccessors;
        }

    }

    public static class OrgSpringframeworkBootSpringBootStarterDataVersionAccessors extends VersionFactory  {

        public OrgSpringframeworkBootSpringBootStarterDataVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.springframework.boot.spring.boot.starter.data.jpa (3.2.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJpa() { return getVersion("org.springframework.boot.spring.boot.starter.data.jpa"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
