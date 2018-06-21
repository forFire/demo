//
// (powered by Fernflower decompiler)
//

package com.flyer.mybatisGenerator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.ClassloaderUtility;
import org.mybatis.generator.internal.util.messages.Messages;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class MyBatisGenerator {
    private Configuration configuration;
    private ShellCallback shellCallback;
    private List<GeneratedJavaFile> generatedJavaFiles;
    private List<GeneratedXmlFile> generatedXmlFiles;
    private List<String> warnings;
    private Set<String> projects;

    public MyBatisGenerator(Configuration configuration, ShellCallback shellCallback, List<String> warnings) throws InvalidConfigurationException {
        if(configuration == null) {
            throw new IllegalArgumentException(Messages.getString("RuntimeError.2"));
        } else {
            this.configuration = configuration;
            if(shellCallback == null) {
                this.shellCallback = new DefaultShellCallback(false);
            } else {
                this.shellCallback = shellCallback;
            }

            if(warnings == null) {
                this.warnings = new ArrayList();
            } else {
                this.warnings = warnings;
            }

            this.generatedJavaFiles = new ArrayList();
            this.generatedXmlFiles = new ArrayList();
            this.projects = new HashSet();
            this.configuration.validate();
        }
    }

    public void generate(ProgressCallback callback) throws SQLException, IOException, InterruptedException {
        this.generate(callback, (Set)null, (Set)null);
    }

    public void generate(ProgressCallback callback, Set<String> contextIds) throws SQLException, IOException, InterruptedException {
        this.generate(callback, contextIds, (Set)null);
    }

    public void generate(ProgressCallback callback, Set<String> contextIds, Set<String> fullyQualifiedTableNames) throws SQLException, IOException, InterruptedException {
        if(callback == null) {
            callback = new NullProgressCallback();
        }

        this.generatedJavaFiles.clear();
        this.generatedXmlFiles.clear();
        Object contextsToRun;
        Context context;
        if(contextIds != null && contextIds.size() != 0) {
            contextsToRun = new ArrayList();
            Iterator i$ = this.configuration.getContexts().iterator();

            while(i$.hasNext()) {
                context = (Context)i$.next();
                if(contextIds.contains(context.getId())) {
                    ((List)contextsToRun).add(context);
                }
            }
        } else {
            contextsToRun = this.configuration.getContexts();
        }

        if(this.configuration.getClassPathEntries().size() > 0) {
            ClassLoader classLoader = ClassloaderUtility.getCustomClassloader(this.configuration.getClassPathEntries());
            ObjectFactory.addExternalClassLoader(classLoader);
        }

        int totalSteps = 0;

        Iterator i$;
        for(i$ = ((List)contextsToRun).iterator(); i$.hasNext(); totalSteps += context.getIntrospectionSteps()) {
            context = (Context)i$.next();
        }

        ((ProgressCallback)callback).introspectionStarted(totalSteps);
        i$ = ((List)contextsToRun).iterator();

        while(i$.hasNext()) {
            context = (Context)i$.next();
            context.introspectTables((ProgressCallback)callback, this.warnings, fullyQualifiedTableNames);
        }

        totalSteps = 0;

        for(i$ = ((List)contextsToRun).iterator(); i$.hasNext(); totalSteps += context.getGenerationSteps()) {
            context = (Context)i$.next();
        }

        ((ProgressCallback)callback).generationStarted(totalSteps);
        i$ = ((List)contextsToRun).iterator();

        while(i$.hasNext()) {
            context = (Context)i$.next();
            context.generateFiles((ProgressCallback)callback, this.generatedJavaFiles, this.generatedXmlFiles, this.warnings);
        }

        ((ProgressCallback)callback).saveStarted(this.generatedXmlFiles.size() + this.generatedJavaFiles.size());
        i$ = this.generatedXmlFiles.iterator();

        File targetFile;
        String source;
        File directory;
        while(i$.hasNext()) {
            GeneratedXmlFile gxf = (GeneratedXmlFile)i$.next();
            this.projects.add(gxf.getTargetProject());

            try {
                directory = this.shellCallback.getDirectory(gxf.getTargetProject(), gxf.getTargetPackage());
                targetFile = new File(directory, gxf.getFileName());
                if(targetFile.exists()) {
                    source = gxf.getFormattedContent();
                    this.warnings.add(Messages.getString("Warning.11", targetFile.getAbsolutePath()));
                } else {
                    source = gxf.getFormattedContent();
                }
            } catch (ShellException var14) {
                this.warnings.add(var14.getMessage());
                continue;
            }

            ((ProgressCallback)callback).checkCancel();
            ((ProgressCallback)callback).startTask(Messages.getString("Progress.15", targetFile.getName()));
            this.writeFile(targetFile, source, "UTF-8");
        }

        i$ = this.generatedJavaFiles.iterator();

        while(i$.hasNext()) {
            GeneratedJavaFile gjf = (GeneratedJavaFile)i$.next();
            this.projects.add(gjf.getTargetProject());

            try {
                directory = this.shellCallback.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage());
                targetFile = new File(directory, gjf.getFileName());
                if(targetFile.exists()) {
                    source = gjf.getFormattedContent();
                    this.warnings.add(Messages.getString("Warning.11", targetFile.getAbsolutePath()));
                } else {
                    source = gjf.getFormattedContent();
                }

                ((ProgressCallback)callback).checkCancel();
                ((ProgressCallback)callback).startTask(Messages.getString("Progress.15", targetFile.getName()));
                this.writeFile(targetFile, source, gjf.getFileEncoding());
            } catch (ShellException var13) {
                this.warnings.add(var13.getMessage());
            }
        }

        i$ = this.projects.iterator();

        while(i$.hasNext()) {
            String project = (String)i$.next();
            this.shellCallback.refreshProject(project);
        }

        ((ProgressCallback)callback).done();
    }

    private void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if(fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    private File getUniqueFileName(File directory, String fileName) {
        File answer = null;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < 1000; ++i) {
            sb.setLength(0);
            sb.append(fileName);
            sb.append('.');
            sb.append(i);
            File testFile = new File(directory, sb.toString());
            if(!testFile.exists()) {
                answer = testFile;
                break;
            }
        }

        if(answer == null) {
            throw new RuntimeException(Messages.getString("RuntimeError.3", directory.getAbsolutePath()));
        } else {
            return answer;
        }
    }
}
