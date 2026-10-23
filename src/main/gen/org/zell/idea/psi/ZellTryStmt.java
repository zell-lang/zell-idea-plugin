// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellTryStmt extends PsiElement {

  @NotNull
  List<ZellBlock> getBlockList();

  @NotNull
  ZellVarName getVarName();

}
