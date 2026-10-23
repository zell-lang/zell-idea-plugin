// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellSwitchCase extends PsiElement {

  @NotNull
  List<ZellPattern> getPatternList();

  @NotNull
  List<ZellStatement> getStatementList();

}
