// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellStatement extends PsiElement {

  @Nullable
  ZellAssignStmt getAssignStmt();

  @Nullable
  ZellBlock getBlock();

  @Nullable
  ZellBreakStmt getBreakStmt();

  @Nullable
  ZellConstDecl getConstDecl();

  @Nullable
  ZellContinueStmt getContinueStmt();

  @Nullable
  ZellDeferStmt getDeferStmt();

  @Nullable
  ZellEos getEos();

  @Nullable
  ZellExprStmt getExprStmt();

  @Nullable
  ZellForStmt getForStmt();

  @Nullable
  ZellIfStmt getIfStmt();

  @Nullable
  ZellReturnStmt getReturnStmt();

  @Nullable
  ZellSwitchStmt getSwitchStmt();

  @Nullable
  ZellTryStmt getTryStmt();

  @Nullable
  ZellVarDecl getVarDecl();

}
