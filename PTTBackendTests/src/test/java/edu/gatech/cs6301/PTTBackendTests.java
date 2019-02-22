package edu.gatech.cs6301;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.*;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.skyscreamer.jsonassert.JSONAssert;

public class PTTBackendTests {

    private String baseUrl = "http://localhost:8080";
    private PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private CloseableHttpClient httpclient;
    private boolean setupdone;

    @Before
    public void runBefore() {
        if (!setupdone) {
            System.out.println("*** SETTING UP TESTS ***");
            // Increase max total connection to 100
            cm.setMaxTotal(100);
            // Increase default max connection per route to 20
            cm.setDefaultMaxPerRoute(10);
            // Increase max connections for localhost:80 to 50
            HttpHost localhost = new HttpHost("locahost", 8080);
            cm.setMaxPerRoute(new HttpRoute(localhost), 10);
            httpclient = HttpClients.custom().setConnectionManager(cm).build();
            setupdone = true;
        }
        System.out.println("*** STARTING TEST ***");
    }

    @After
    public void runAfter() {
        System.out.println("*** ENDING TEST ***");
    }

    // *** YOU SHOULD NOT NEED TO CHANGE ANYTHING ABOVE THIS LINE ***

    // USER HERE

    @Test
    public void createUserTest() throws Exception {
        // TODO: convert to deleteUsers()
        deleteContacts();
        try {
            CloseableHttpResponse response =
                    createUser("John", "Doe", "john@doe.org");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            String strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String id = getIdFromStringResponse(strResponse);

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void updateUserTest() throws Exception {
        // TODO: convert to deleteUsers()
        deleteContacts();
        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            String id = getIdFromResponse(response);
            response.close();

            response = updateUser(id, "Tom", "Doe", "tom@doe.org");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"Tom\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"tom@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getUserTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();
        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            String id = getIdFromResponse(response);
            // EntityUtils.consume(response.getEntity());
            response.close();

            response = getContact(id);

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getAllUsersTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();
        String id = null;
        String expectedJson = "";

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            id = getIdFromResponse(response);
            expectedJson += "[{\"id\":" + id + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            response.close();

            response = createUser("Jane", "Wall", "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            id = getIdFromResponse(response);
            expectedJson += ",{\"id\":" + id + ",\"firstName\":\"Jane\",\"lastName\":\"Wall\",\"email\":\"jane@wall.com\"}]";
            response.close();

            response = getAllUsers();

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void DeleteUserTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();
        String expectedJson = null;

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String deleteid = getIdFromResponse(response);
            response.close();

            int status;
            HttpEntity entity;
            String strResponse;

            response = deleteUser(deleteid);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            expectedJson = "{\"id\":" + deleteid + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getAllUsers();
            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            expectedJson = "[]";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void CreateMultipleDeleteOneUserTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();
        String expectedJson = "";

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String deleteId = getIdFromResponse(response);
            response.close();

            response = createUser("Jane", "Wall", "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String id = getIdFromResponse(response);
            expectedJson += "[{\"id\":" + id + ",\"firstName\":\"Jane\",\"lastName\":\"Wall\",\"email\":\"jane@wall.com\"}]";
            response.close();

            int status;
            HttpEntity entity;
            String strResponse;

            response = deleteUser(deleteId);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson2 = "{\"id\":" + deleteId + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson2,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getAllUsers();
            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            // expectedJson = "[]";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void CreateMultipleUpdateOneUserTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id = getIdFromResponse(response);
            response.close();

            response = createUser("Jane", "Wall", "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String updatedId = getIdFromResponse(response);
            response.close();

            int status;
            HttpEntity entity;
            String strResponse;

            response = updateUser(updatedId, "Jane", "Wall", "jane@wall.com");
            String expectedJson = "{\"id\":" + updatedId + ",\"firstName\":\"Jane\",\"lastName\":\"Wall\",\"email\":\"jane@wall.com\"}";

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            // Check that the record is correct in the response
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getUser(updatedId);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            // Check that the record was correctly updated in the addressbook
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getMissingUserTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id1 = getIdFromResponse(response);
            response.close();

            response = createUser("Jane", "Wall", "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String id2 = getIdFromResponse(response);
            response.close();

            String missingId = "xyz" + id1 + id2; // making sure the ID is not present

            response = getUser(missingId);

            int status = response.getStatusLine().getStatusCode();
            Assert.assertEquals(404, status);

            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void deleteMissingUserTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id1 = getIdFromResponse(response);
            response.close();

            response = createUser("Jane", "Wall", "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String id2 = getIdFromResponse(response);
            response.close();

            String missingId = "xyz" + id1 + id2; // making sure the ID is not present

            response = deleteUser(missingId);

            int status = response.getStatusLine().getStatusCode();
            Assert.assertEquals(404, status);

            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    // PROJECT HERE
    @Test
    public void createProjectTest() throws Exception {
        //How to Clear?? 
        deleteContacts();

       try {
            //Create a User first
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            String strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String id = getIdFromStringResponse(strResponse);

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            //Creat a project based on the user's id
            response = 
                    createProject(id, "project 1");
            status = response.getStatusLine().getStatusCode();
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String projectid = getIdFromStringResponse(strResponse);

            String expectedJson = "{\"id\":" + projectid + ",\"projectname\":\"project 1\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

        } finally {
            httpclient.close();
        }
    }

    @Test
    public void updateProjectTest() throws Exception {
        // TODO: convert to deleteUsers()
        deleteContacts();
        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            String id = getIdFromResponse(response);
            response.close();

            response = createProject(id, "project 1");
            String projectid = getIdFromResponse(response);
            response.close();

            response = updateProject(id, projectid, "project 2");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + projectid + ",\"projectname\":\"project 2\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getProjectTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();
        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            String id = getIdFromResponse(response);
            // EntityUtils.consume(response.getEntity());
            response.close();

            response = createProject(id, "project 1");
            String projectid = getIdFromResponse(response);
            response.close();

            response = getProject(id, projectid);

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + projectid + ",\"projectname\":\"project 1\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getAllProjectsTest() throws Exception {
        httpclient = HttpClients.createDefault();
        // TODO: convert to deleteUsers()
        deleteContacts();
        String id = null;
        String projectid = null;
        String expectedJson = "";

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            id = getIdFromResponse(response);
            response.close();

            response = createProject(id, "project 1");
            // EntityUtils.consume(response.getEntity());
            projectid = getIdFromResponse(response);
            expectedJson += "[{\"id\":" + projectid + ",\"projectname\":\"project 1\"}";
            response.close();

            response = createProject(id, "project 2");
            projectid = getIdFromResponse(response);
            expectedJson += "{\"id\":" + projectid + ",\"projectname\":\"project 2\"}";
            response.close();

            response = createProject(id, "project 3");
            projectid = getIdFromResponse(response);
            expectedJson += "{\"id\":" + projectid + ",\"projectname\":\"project 3\"}]";
            response.close();

            response = getAllProjects();

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void DeleteProjectTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();
        String expectedJson = null;

        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id = getIdFromResponse(response);
            response.close();

            response = createProject(id, "project 1");
            String projectid = getIdFromResponse(response);
            response.close(); 

            int status;
            HttpEntity entity;
            String strResponse;

            response = deleteProject(id, projectid);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            expectedJson = "{\"id\":" + projectid + ",\"projectname\":\"project 1\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getAllProjects(id);
            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            expectedJson = "[]";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    // SESSION HERE
    @Test
    public void createSessionTest() throws Exception {
        //How to Clear?? 
        deleteContacts();

       try {
            //Create a User first
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            String strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String id = getIdFromStringResponse(strResponse);

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            //Create a project based on the user's id
            response =  createProject(id, "project 1");
            status = response.getStatusLine().getStatusCode();
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String projectid = getIdFromStringResponse(strResponse);

            String expectedJson = "{\"id\":" + projectid + ",\"projectname\":\"project 1\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
            //Create session based on user and project's id

            response = createSession(id, projectid,"2019-02-18T20:00Z","2019-02-18T21:00Z",0);
            status = response.getStatusLine().getStatusCode();
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);
            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");
            String sessionid = getIdFromStringResponse(strResponse);
            String expectedJson = "{\"id\":" + sessionid + ",\"startTime\":\"2019-02-18T20:00Z\",\"endTime\":\"2019-02-18T21:00Z\",\"counter\":" +counter+ "}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void updateSessionTest() throws Exception {
        // TODO: convert to deleteUsers()
        deleteContacts();
        try {
            CloseableHttpResponse response = createUser("John", "Doe", "john@doe.org");
            String id = getIdFromResponse(response);
            response.close();

            response = createProject(id, "project 1");
            String projectid = getIdFromResponse(response);
            response.close();

            response = createSession(id, projectid,"2019-02-18T20:00Z","2019-02-18T21:00Z",0);
            String sessionid = getIdFromResponse(response);
            response.close();

            response = updateSession(id, projectid, sessionid, "2019-02-20T22:00Z",1);

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + sessionid + ",\"startTime\":\"2019-02-18T20:00Z\",\"endTime\":\"2019-02-20T22:00Z\",\"counter\":" + counter + "}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

        } finally {
            httpclient.close();
        }
    }

    

    // REPORT HERE

    // OLD CODE BELOW

    @Test
    public void createContactTest() throws Exception {
        deleteContacts();

        try {
            CloseableHttpResponse response =
                    createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            if (status == 201) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            String strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String id = getIdFromStringResponse(strResponse);

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"John\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void updateContactTest() throws Exception {
        deleteContacts();

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            String id = getIdFromResponse(response);
            response.close();

            response = updateContact(id, "Tom", "Doe", "(123)-456-7890" , "tom@doe.org");

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"Tom\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"tom@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getContactTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            String id = getIdFromResponse(response);
            // EntityUtils.consume(response.getEntity());
            response.close();

            response = getContact(id);

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson = "{\"id\":" + id + ",\"firstName\":\"John\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getAllContactsTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();
        String id = null;
        String expectedJson = "";

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            id = getIdFromResponse(response);
            expectedJson += "[{\"id\":\"" + id + "\",\"firstname\":\"John\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"john@doe.org\"}";
            response.close();

            response = createContact("Jane", "Wall", "(9876)-543-210" , "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            id = getIdFromResponse(response);
            expectedJson += ",{\"id\":\"" + id + "\",\"firstname\":\"Jane\",\"familyname\":\"Wall\",\"phonenumber\":\"(9876)-543-210\",\"email\":\"jane@wall.com\"}]";
            response.close();

            response = getAllContacts();

            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity;
            String strResponse;
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void DeleteContactTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();
        String expectedJson = null;

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String deleteid = getIdFromResponse(response);
            response.close();

            int status;
            HttpEntity entity;
            String strResponse;

            response = deleteContact(deleteid);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            expectedJson = "{\"id\":\"" + deleteid + "\",\"firstname\":\"John\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getAllContacts();
            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            expectedJson = "[]";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void CreateMultipleDeleteOneContactTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();
        String expectedJson = "";

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String deleteId = getIdFromResponse(response);
            response.close();

            response = createContact("Jane", "Wall", "(9876)-543-210" , "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String id = getIdFromResponse(response);
            expectedJson += "[{\"id\":\"" + id + "\",\"firstname\":\"Jane\",\"familyname\":\"Wall\",\"phonenumber\":\"(9876)-543-210\",\"email\":\"jane@wall.com\"}]";
            response.close();

            int status;
            HttpEntity entity;
            String strResponse;

            response = deleteContact(deleteId);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            String expectedJson2 = "{\"id\":\"" + deleteId + "\",\"firstname\":\"John\",\"familyname\":\"Doe\",\"phonenumber\":\"(123)-456-7890\",\"email\":\"john@doe.org\"}";
            JSONAssert.assertEquals(expectedJson2,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getAllContacts();
            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            // expectedJson = "[]";
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void CreateMultipleUpdateOneContactTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id = getIdFromResponse(response);
            response.close();

            response = createContact("Jane", "Wall", "(9876)-543-210" , "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String updatedId = getIdFromResponse(response);
            response.close();

            int status;
            HttpEntity entity;
            String strResponse;

            response = updateContact(updatedId, "Jane", "Wall", "(6789)-210-534" , "jane@wall.com");
            String expectedJson = "{\"id\":\"" + updatedId + "\",\"firstname\":\"Jane\",\"familyname\":\"Wall\",\"phonenumber\":\"(6789)-210-534\",\"email\":\"jane@wall.com\"}";

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            // Check that the record is correct in the response
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

            response = getContact(updatedId);

            status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                entity = response.getEntity();
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
            strResponse = EntityUtils.toString(entity);

            System.out.println("*** String response " + strResponse + " (" + response.getStatusLine().getStatusCode() + ") ***");

            // Check that the record was correctly updated in the addressbook
            JSONAssert.assertEquals(expectedJson,strResponse, false);
            EntityUtils.consume(response.getEntity());
            response.close();

        } finally {
            httpclient.close();
        }
    }

    @Test
    public void getMissingContactTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id1 = getIdFromResponse(response);
            response.close();

            response = createContact("Jane", "Wall", "(9876)-543-210" , "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String id2 = getIdFromResponse(response);
            response.close();

            String missingId = "xyz" + id1 + id2; // making sure the ID is not present

            response = getContact(missingId);

            int status = response.getStatusLine().getStatusCode();
            Assert.assertEquals(404, status);

            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    @Test
    public void deleteMissingContactTest() throws Exception {
        httpclient = HttpClients.createDefault();
        deleteContacts();

        try {
            CloseableHttpResponse response = createContact("John", "Doe", "(123)-456-7890" , "john@doe.org");
            // EntityUtils.consume(response.getEntity());
            String id1 = getIdFromResponse(response);
            response.close();

            response = createContact("Jane", "Wall", "(9876)-543-210" , "jane@wall.com");
            // EntityUtils.consume(response.getEntity());
            String id2 = getIdFromResponse(response);
            response.close();

            String missingId = "xyz" + id1 + id2; // making sure the ID is not present

            response = deleteContact(missingId);

            int status = response.getStatusLine().getStatusCode();
            Assert.assertEquals(404, status);

            EntityUtils.consume(response.getEntity());
            response.close();
        } finally {
            httpclient.close();
        }
    }

    // METHODS HERE

    // USER

    private CloseableHttpResponse createUser(String firstName, String lastName, String email) throws IOException {
        HttpPost httpRequest = new HttpPost(baseUrl + "/ptt/users");
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\"" + lastName + "\"," +
                "\"email\":\"" + email + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse updateUser(String id, String firstName, String lastName, String email) throws IOException {
        HttpPut httpRequest = new HttpPut(baseUrl + "/ptt/users/" + id);
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\"" + lastName + "\"," +
                "\"email\":\"" + email + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse getUser(String id) throws IOException {
        HttpGet httpRequest = new HttpGet(baseUrl + "/ptt/users/" + id);
        httpRequest.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse getAllUsers() throws IOException {
        HttpGet httpRequest = new HttpGet(baseUrl + "/ptt/users");
        httpRequest.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse deleteUser(String id) throws IOException {
        HttpDelete httpDelete = new HttpDelete(baseUrl + "/ptt/users/" + id);
        httpDelete.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpDelete.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        System.out.println("*** Raw response " + response + "***");
        // EntityUtils.consume(response.getEntity());
        // response.close();
        return response;
    }

    // PROJECT
    private CloseableHttpResponse createProject(String userid, String projectname) throws IOException {
        HttpPost httpRequest = new HttpPost(baseUrl + "/ptt/users/" + userid + "/projects");
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"projectname\":\"" + projectname + "\"," +
                "\"userid\":\"" + userid + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse updateProject(String userid, String projectid, String projectname) throws IOException {
        HttpPut httpRequest = new HttpPut(baseUrl + "/ptt/users/" + userid + "/projects/" + projectid);
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"projectname\":\"" + projectname + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse getProject(String userid, String projectid) throws IOException {
        HttpGet httpRequest = new HttpGet(baseUrl + "/ptt/users/" + userid + "/projects/" + projectid);
        httpRequest.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }
    // Not Needed?
    private CloseableHttpResponse getAllProjects(String userid) throws IOException {
        HttpGet httpRequest = new HttpGet(baseUrl + "/ptt/users/" + userid);
        httpRequest.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse deleteProject(String userid, String projectid) throws IOException {
        HttpDelete httpDelete = new HttpDelete(baseUrl + "/ptt/users/" + userid + "/projects/" + projectid);
        httpDelete.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpDelete.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        System.out.println("*** Raw response " + response + "***");
        // EntityUtils.consume(response.getEntity());
        // response.close();
        return response;
    }
    // SESSION
    private CloseableHttpResponse createSession(String userid, String projectid, String startTime, String endTime, Integer counter) throws IOException {
        HttpPost httpRequest = new HttpPost(baseUrl + "/ptt/users/" + userid + "/projects/" + projectid + "/sessions");
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"startTime\":\"" + startTime + "\"," +
                "\"endTime\":\"" + endTime + "\"," +
                "\"counter\":\"" + counter + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse updateSession(String userid, String projectid, String sessionid, String endTime, Integer counter) throws IOException {
        HttpPut httpRequest = new HttpPut(baseUrl + "/ptt/users/" + userid + "/projects/" + projectid + "/sessions/" +sessionid);
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"endTime\":\"" + endTime + "\"," +
                "\"counter\":\"" + counter + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }
    // REPORT

    // GET ID from Response

    private String getIdFromResponse(CloseableHttpResponse response) throws IOException, JSONException {
        HttpEntity entity = response.getEntity();
        String strResponse = EntityUtils.toString(entity);
        String id = getIdFromStringResponse(strResponse);
        return id;
    }

    private String getIdFromStringResponse(String strResponse) throws JSONException {
        JSONObject object = new JSONObject(strResponse);

        String id = null;
        Iterator<String> keyList = object.keys();
        while (keyList.hasNext()){
            String key = keyList.next();
            if (key.equals("id")) {
                id = object.get(key).toString();
            }
        }
        return id;
    }

    // OLD CODE BELOW

    private CloseableHttpResponse createContact(String firstname, String familyname, String phonenumber, String email) throws IOException {
        HttpPost httpRequest = new HttpPost(baseUrl + "/api/contacts");
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"firstname\":\"" + firstname + "\"," +
                "\"familyname\":\"" + familyname + "\"," +
                "\"phonenumber\":\"" + phonenumber + "\"," +
                "\"email\":\"" + email + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse updateContact(String id, String firstname, String familyname, String phonenumber, String email) throws IOException {
        HttpPut httpRequest = new HttpPut(baseUrl + "/api/contacts/" + id);
        httpRequest.addHeader("accept", "application/json");
        StringEntity input = new StringEntity("{\"firstname\":\"" + firstname + "\"," +
                "\"familyname\":\"" + familyname + "\"," +
                "\"phonenumber\":\"" + phonenumber + "\"," +
                "\"email\":\"" + email + "\"}");
        input.setContentType("application/json");
        httpRequest.setEntity(input);

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse getContact(String id) throws IOException {
        HttpGet httpRequest = new HttpGet(baseUrl + "/api/contacts/" + id);
        httpRequest.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse getAllContacts() throws IOException {
        HttpGet httpRequest = new HttpGet(baseUrl + "/api/contacts");
        httpRequest.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpRequest.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpRequest);
        System.out.println("*** Raw response " + response + "***");
        return response;
    }

    private CloseableHttpResponse deleteContact(String id) throws IOException {
        HttpDelete httpDelete = new HttpDelete(baseUrl + "/api/contacts/" + id);
        httpDelete.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpDelete.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        System.out.println("*** Raw response " + response + "***");
        // EntityUtils.consume(response.getEntity());
        // response.close();
        return response;
    }

    // NOT NEEDED!
    private CloseableHttpResponse deleteContacts() throws IOException {
        HttpDelete httpDelete = new HttpDelete(baseUrl + "/api/contacts");
        httpDelete.addHeader("accept", "application/json");

        System.out.println("*** Executing request " + httpDelete.getRequestLine() + "***");
        CloseableHttpResponse response = httpclient.execute(httpDelete);
        System.out.println("*** Raw response " + response + "***");
        // EntityUtils.consume(response.getEntity());
        // response.close();
        return response;
    }



}
