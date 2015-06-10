/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-03-26 20:30:19 UTC)
 * on 2015-04-27 at 15:57:26 UTC 
 * Modify at your own risk.
 */

package com.appspot.myapplicationid.myApi;

/**
 * Service definition for MyApi (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link MyApiRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class MyApi extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.20.0 of the myApi library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://myApplicationId.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "myApi/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public MyApi(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  MyApi(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "sayHi".
   *
   * This request holds the parameters needed by the myApi server.  After setting any optional
   * parameters, call the {@link SayHi#execute()} method to invoke the remote operation.
   *
   * @param name
   * @return the request
   */
  public SayHi sayHi(java.lang.String name) throws java.io.IOException {
    SayHi result = new SayHi(name);
    initialize(result);
    return result;
  }

  public class SayHi extends MyApiRequest<com.appspot.myapplicationid.myApi.model.MyBean> {

    private static final String REST_PATH = "sayHi/{name}";

    /**
     * Create a request for the method "sayHi".
     *
     * This request holds the parameters needed by the the myApi server.  After setting any optional
     * parameters, call the {@link SayHi#execute()} method to invoke the remote operation. <p> {@link
     * SayHi#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param name
     * @since 1.13
     */
    protected SayHi(java.lang.String name) {
      super(MyApi.this, "POST", REST_PATH, null, com.appspot.myapplicationid.myApi.model.MyBean.class);
      this.name = com.google.api.client.util.Preconditions.checkNotNull(name, "Required parameter name must be specified.");
    }

    @Override
    public SayHi setAlt(java.lang.String alt) {
      return (SayHi) super.setAlt(alt);
    }

    @Override
    public SayHi setFields(java.lang.String fields) {
      return (SayHi) super.setFields(fields);
    }

    @Override
    public SayHi setKey(java.lang.String key) {
      return (SayHi) super.setKey(key);
    }

    @Override
    public SayHi setOauthToken(java.lang.String oauthToken) {
      return (SayHi) super.setOauthToken(oauthToken);
    }

    @Override
    public SayHi setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (SayHi) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public SayHi setQuotaUser(java.lang.String quotaUser) {
      return (SayHi) super.setQuotaUser(quotaUser);
    }

    @Override
    public SayHi setUserIp(java.lang.String userIp) {
      return (SayHi) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String name;

    /**

     */
    public java.lang.String getName() {
      return name;
    }

    public SayHi setName(java.lang.String name) {
      this.name = name;
      return this;
    }

    @Override
    public SayHi set(String parameterName, Object value) {
      return (SayHi) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "storeData".
   *
   * This request holds the parameters needed by the myApi server.  After setting any optional
   * parameters, call the {@link StoreData#execute()} method to invoke the remote operation.
   *
   * @param eid
   * @param uname
   * @param password
   * @return the request
   */
  public StoreData storeData(java.lang.String eid, java.lang.String uname, java.lang.String password) throws java.io.IOException {
    StoreData result = new StoreData(eid, uname, password);
    initialize(result);
    return result;
  }

  public class StoreData extends MyApiRequest<com.appspot.myapplicationid.myApi.model.MyBean> {

    private static final String REST_PATH = "storeData/{eid}/{uname}/{password}";

    /**
     * Create a request for the method "storeData".
     *
     * This request holds the parameters needed by the the myApi server.  After setting any optional
     * parameters, call the {@link StoreData#execute()} method to invoke the remote operation. <p>
     * {@link
     * StoreData#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param eid
     * @param uname
     * @param password
     * @since 1.13
     */
    protected StoreData(java.lang.String eid, java.lang.String uname, java.lang.String password) {
      super(MyApi.this, "POST", REST_PATH, null, com.appspot.myapplicationid.myApi.model.MyBean.class);
      this.eid = com.google.api.client.util.Preconditions.checkNotNull(eid, "Required parameter eid must be specified.");
      this.uname = com.google.api.client.util.Preconditions.checkNotNull(uname, "Required parameter uname must be specified.");
      this.password = com.google.api.client.util.Preconditions.checkNotNull(password, "Required parameter password must be specified.");
    }

    @Override
    public StoreData setAlt(java.lang.String alt) {
      return (StoreData) super.setAlt(alt);
    }

    @Override
    public StoreData setFields(java.lang.String fields) {
      return (StoreData) super.setFields(fields);
    }

    @Override
    public StoreData setKey(java.lang.String key) {
      return (StoreData) super.setKey(key);
    }

    @Override
    public StoreData setOauthToken(java.lang.String oauthToken) {
      return (StoreData) super.setOauthToken(oauthToken);
    }

    @Override
    public StoreData setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (StoreData) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public StoreData setQuotaUser(java.lang.String quotaUser) {
      return (StoreData) super.setQuotaUser(quotaUser);
    }

    @Override
    public StoreData setUserIp(java.lang.String userIp) {
      return (StoreData) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String eid;

    /**

     */
    public java.lang.String getEid() {
      return eid;
    }

    public StoreData setEid(java.lang.String eid) {
      this.eid = eid;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String uname;

    /**

     */
    public java.lang.String getUname() {
      return uname;
    }

    public StoreData setUname(java.lang.String uname) {
      this.uname = uname;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String password;

    /**

     */
    public java.lang.String getPassword() {
      return password;
    }

    public StoreData setPassword(java.lang.String password) {
      this.password = password;
      return this;
    }

    @Override
    public StoreData set(String parameterName, Object value) {
      return (StoreData) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link MyApi}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link MyApi}. */
    @Override
    public MyApi build() {
      return new MyApi(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link MyApiRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setMyApiRequestInitializer(
        MyApiRequestInitializer myapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(myapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
