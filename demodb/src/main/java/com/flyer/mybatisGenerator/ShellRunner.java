/**
 * @auth likun
 */

package com.flyer.mybatisGenerator;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.messages.Messages;
import org.mybatis.generator.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ShellRunner {
    private static final String CONFIG_FILE = "-configfile";
    private static final String OVERWRITE = "-overwrite";
    private static final String CONTEXT_IDS = "-contextids";
    private static final String TABLES = "-tables";
    private static final String VERBOSE = "-verbose";
    private static final String FORCE_JAVA_LOGGING = "-forceJavaLogging";
    private static final String HELP_1 = "-?";
    private static final String HELP_2 = "-h";

    public ShellRunner() {
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
            System.exit(0);
        } else {
            Map<String, String> arguments = parseCommandLine(args);
            if (arguments.containsKey("-?")) {
                usage();
                System.exit(0);
            } else if (!arguments.containsKey("-configfile")) {
                writeLine(Messages.getString("RuntimeError.0"));
            } else {
                List<String> warnings = new ArrayList();
                String configfile = (String) arguments.get("-configfile");
                File configurationFile = new File(configfile);
                if (!configurationFile.exists()) {
                    writeLine(Messages.getString("RuntimeError.1", configfile));
                } else {
                    Set<String> fullyqualifiedTables = new HashSet();
                    if (arguments.containsKey("-tables")) {
                        StringTokenizer st = new StringTokenizer((String) arguments.get("-tables"), ",");

                        while (st.hasMoreTokens()) {
                            String s = st.nextToken().trim();
                            if (s.length() > 0) {
                                fullyqualifiedTables.add(s);
                            }
                        }
                    }

                    Set<String> contexts = new HashSet();
                    String warning;
                    if (arguments.containsKey("-contextids")) {
                        StringTokenizer st = new StringTokenizer((String) arguments.get("-contextids"), ",");

                        while (st.hasMoreTokens()) {
                            warning = st.nextToken().trim();
                            if (warning.length() > 0) {
                                contexts.add(warning);
                            }
                        }
                    }

                    String error;
                    Iterator i$;
                    try {
                        ConfigurationParser cp = new ConfigurationParser(warnings);
                        Configuration config = cp.parseConfiguration(configurationFile);
                        DefaultShellCallback shellCallback = new DefaultShellCallback(arguments.containsKey("-overwrite"));
                        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);
                        ProgressCallback progressCallback = arguments.containsKey("-verbose") ? new VerboseProgressCallback() : null;
                        myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);
                    } catch (XMLParserException var12) {
                        writeLine(Messages.getString("Progress.3"));
                        writeLine();
                        i$ = var12.getErrors().iterator();

                        while (i$.hasNext()) {
                            error = (String) i$.next();
                            writeLine(error);
                        }

                        return;
                    } catch (SQLException var13) {
                        var13.printStackTrace();
                        return;
                    } catch (IOException var14) {
                        var14.printStackTrace();
                        return;
                    } catch (InvalidConfigurationException var15) {
                        writeLine(Messages.getString("Progress.16"));
                        i$ = var15.getErrors().iterator();

                        while (i$.hasNext()) {
                            error = (String) i$.next();
                            writeLine(error);
                        }

                        return;
                    } catch (InterruptedException var16) {
                        ;
                    }

                    i$ = warnings.iterator();

                    while (i$.hasNext()) {
                        warning = (String) i$.next();
                        writeLine(warning);
                    }

                    if (warnings.size() == 0) {
                        writeLine(Messages.getString("Progress.4"));
                    } else {
                        writeLine();
                        writeLine(Messages.getString("Progress.5"));
                    }

                }
            }
        }
    }

    private static void usage() {
        String lines = Messages.getString("Usage.Lines");
        int iLines = Integer.parseInt(lines);

        for (int i = 0; i < iLines; ++i) {
            String key = "Usage." + i;
            writeLine(Messages.getString(key));
        }

    }

    private static void writeLine(String message) {
        System.out.println(message);
    }

    private static void writeLine() {
        System.out.println();
    }

    private static Map<String, String> parseCommandLine(String[] args) {
        List<String> errors = new ArrayList();
        Map<String, String> arguments = new HashMap();

        for (int i = 0; i < args.length; ++i) {
            if ("-configfile".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-configfile", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-configfile"));
                }

                ++i;
            } else if ("-overwrite".equalsIgnoreCase(args[i])) {
                arguments.put("-overwrite", "Y");
            } else if ("-verbose".equalsIgnoreCase(args[i])) {
                arguments.put("-verbose", "Y");
            } else if ("-?".equalsIgnoreCase(args[i])) {
                arguments.put("-?", "Y");
            } else if ("-h".equalsIgnoreCase(args[i])) {
                arguments.put("-?", "Y");
            } else if ("-forceJavaLogging".equalsIgnoreCase(args[i])) {
                LogFactory.forceJavaLogging();
            } else if ("-contextids".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-contextids", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-contextids"));
                }

                ++i;
            } else if ("-tables".equalsIgnoreCase(args[i])) {
                if (i + 1 < args.length) {
                    arguments.put("-tables", args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.19", "-tables"));
                }

                ++i;
            } else {
                errors.add(Messages.getString("RuntimeError.20", args[i]));
            }
        }

        if (!errors.isEmpty()) {
            Iterator i$ = errors.iterator();

            while (i$.hasNext()) {
                String error = (String) i$.next();
                writeLine(error);
            }

            System.exit(-1);
        }

        return arguments;
    }
}
