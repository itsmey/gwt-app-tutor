package com.ivan.server;

import com.filenet.api.constants.*;
import com.filenet.api.collection.IndependentObjectSet;
import com.filenet.api.core.*;
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.api.property.Properties;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.util.UserContext;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class FileNetManager {

    private static Connection connection;
    private static ObjectStore objectStore;

    private static String workingDir = "/";
    private static String login;
    private static String password;

    private static void pushSubject() {
        Subject subject = UserContext.createSubject(connection, login, password, null);
        UserContext.get().pushSubject(subject);
    }

    private static void popSubject() {
        UserContext.get().popSubject();
    }

    static String getWorkingDir() {
        return workingDir;
    }

    static void setWorkingDir(String value) {
        workingDir = value;
    }

    static void connect(String lgn, String pwd, String objectStoreName)
            throws Exception {
        String uri = "http://172.19.215.15:9080/wsi/FNCEWS40MTOM/";

        connection = Factory.Connection.getConnection(uri);

        login = lgn;
        password = pwd;

        pushSubject();

        try {
            Domain domain = Factory.Domain.getInstance(connection, null);

            objectStore = Factory.ObjectStore.fetchInstance(domain,
                    objectStoreName, null);

        } catch (EngineRuntimeException ex) {
            throw new Exception(ex.getExceptionCode().toString());
        } finally {
            popSubject();
        }
    }

    private static List<String> performSql(String query) {
        SearchScope scope = new SearchScope(objectStore);
        SearchSQL sqlQuery = new SearchSQL(query);
        List<String> result = new ArrayList<>();

        pushSubject();
        try {
            IndependentObjectSet resultSet = scope.fetchObjects(sqlQuery, null, null, false);

            for (Iterator i = resultSet.iterator(); i.hasNext(); ) {
                Containable entry = (Containable)i.next();
                result.add(entry.get_Name());
            }
        }
        finally {
            popSubject();
        }

        return result;
    }

    private static List<String> browse(String className, String pathName) {
        String query = "SELECT * FROM " + className +
                " WHERE This infolder '" + pathName + "'";

        return performSql(query);
    }

    private static List<String> getFolders() {
        return browse("Folder", getWorkingDir());
    }

    private static List<String> getDocuments() {
        return browse("Document", getWorkingDir());
    }

    static List<String> getAll() {
        List<String> result = getFolders();
        result.addAll(getDocuments());

        return result;
    }

    static void deleteDocument(String name) throws Exception {
        pushSubject();
        try {
            Document doc = Factory.Document.getInstance(objectStore, "Document",
                    getWorkingDir() + "/" + name);
            doc.delete();
            doc.save(RefreshMode.NO_REFRESH);

        } catch (EngineRuntimeException ex) {
            throw new Exception(ex.getExceptionCode().getDefaultEnglishString());
        }
        finally {
            popSubject();
        }
    }

    static void deleteFolder(String name) throws Exception {
        pushSubject();
        try {
            Folder f = Factory.Folder.getInstance(objectStore, "Folder",
                    getWorkingDir() + "/" + name);
            f.delete();
            f.save(RefreshMode.NO_REFRESH);

        } catch (EngineRuntimeException ex) {
            throw new Exception(ex.getExceptionCode().getDefaultEnglishString());
        }
        finally {
            popSubject();
        }
    }

    static void createDocument(String name) throws Exception {
        pushSubject();
        try {
            Folder parent_folder = Factory.Folder.fetchInstance(objectStore,
                    getWorkingDir(), null);

            Document doc = Factory.Document.createInstance(objectStore, "Document");

            Properties serverDocProps = doc.getProperties();
            serverDocProps.putValue("DocumentTitle", name);

            doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
            doc.save(RefreshMode.REFRESH);

            parent_folder.file(doc, AutoUniqueName.AUTO_UNIQUE, null,
                    DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE).save(
                    RefreshMode.NO_REFRESH);

        } catch (EngineRuntimeException ex) {
            throw new Exception(ex.getExceptionCode().getDefaultEnglishString());
        }
        finally {
            popSubject();
        }
    }

    static void createFolder(String name) throws Exception {
        pushSubject();
        try {
            Folder parent_folder = Factory.Folder.fetchInstance(objectStore,
                    getWorkingDir(), null);
            Folder f = parent_folder.createSubFolder(name);
            f.save(RefreshMode.REFRESH);

        } catch (EngineRuntimeException ex) {
            throw new Exception(ex.getExceptionCode().getDefaultEnglishString());
        }
        finally {
            popSubject();
        }
    }
}
