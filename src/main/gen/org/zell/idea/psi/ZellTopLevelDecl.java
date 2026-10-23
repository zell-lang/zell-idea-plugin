// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellTopLevelDecl extends PsiElement {

  @NotNull
  List<ZellAttribute> getAttributeList();

  @Nullable
  ZellConstDecl getConstDecl();

  @Nullable
  ZellEnumDecl getEnumDecl();

  @Nullable
  ZellEos getEos();

  @Nullable
  ZellExternFuncDecl getExternFuncDecl();

  @Nullable
  ZellFuncDecl getFuncDecl();

  @Nullable
  ZellInterfaceDecl getInterfaceDecl();

  @Nullable
  ZellStructDecl getStructDecl();

  @Nullable
  ZellVarDecl getVarDecl();

}
