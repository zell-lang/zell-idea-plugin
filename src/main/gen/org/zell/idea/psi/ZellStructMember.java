// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZellStructMember extends PsiElement {

  @NotNull
  List<ZellAttribute> getAttributeList();

  @Nullable
  ZellEmbedDecl getEmbedDecl();

  @Nullable
  ZellEos getEos();

  @Nullable
  ZellFieldDecl getFieldDecl();

  @Nullable
  ZellMethodDecl getMethodDecl();

}
