/*
 * SonarQube Java
 * Copyright (C) 2012 SonarSource
 * sonarqube@googlegroups.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.java.checks;

import org.sonar.java.model.VisitorsBridge;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;
import org.junit.Test;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.squidbridge.api.SourceFile;

import java.io.File;

public class MethodComplexityCheckTest {

  private MethodComplexityCheck check = new MethodComplexityCheck();

  @Test
  public void defaults() {
    SourceFile file = JavaAstScanner.scanSingleFile(new File("src/test/files/checks/MethodComplexity.java"), new VisitorsBridge(check));
    CheckMessagesVerifier.verify(file.getCheckMessages())
        .noMore();
  }

  @Test
  public void test() {
    check.setMax(1);
    SourceFile file = JavaAstScanner.scanSingleFile(new File("src/test/files/checks/MethodComplexity.java"), new VisitorsBridge(check));
    CheckMessagesVerifier.verify(file.getCheckMessages())
        .next().atLine(3).withMessage("The Cyclomatic Complexity of this method \"sayHello\" is 2 which is greater than 1 authorized.").withCost(1.0)
        .noMore();
  }

}
