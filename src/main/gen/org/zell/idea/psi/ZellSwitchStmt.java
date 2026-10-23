// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellSwitchStmt extends PsiElement {

  @Nullable
  ZellDefaultCase getDefaultCase();

  @Nullable
  ZellExpr getExpr();

  @NotNull
  List<ZellSwitchCase> getSwitchCaseList();

}
