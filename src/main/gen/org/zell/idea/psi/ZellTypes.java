// This is a generated file. Not intended for manual editing.
package org.zell.idea.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.zell.idea.stubs.ZellStubElementTypes;
import org.zell.idea.psi.impl.*;

public interface ZellTypes {

  IElementType ADD_EXPR = new ZellElementType("ADD_EXPR");
  IElementType AND_EXPR = new ZellElementType("AND_EXPR");
  IElementType ARG_LIST = new ZellElementType("ARG_LIST");
  IElementType ARRAY_TYPE = new ZellElementType("ARRAY_TYPE");
  IElementType ASSIGN_OP = new ZellElementType("ASSIGN_OP");
  IElementType ASSIGN_STMT = new ZellElementType("ASSIGN_STMT");
  IElementType ATTRIBUTE = new ZellElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_ARG = new ZellElementType("ATTRIBUTE_ARG");
  IElementType ATTRIBUTE_ARGS = new ZellElementType("ATTRIBUTE_ARGS");
  IElementType BLOCK = new ZellElementType("BLOCK");
  IElementType BREAK_STMT = new ZellElementType("BREAK_STMT");
  IElementType CALL_ARG = new ZellElementType("CALL_ARG");
  IElementType CMP_EXPR = new ZellElementType("CMP_EXPR");
  IElementType CONST_DECL = ZellStubElementTypes.factory("CONST_DECL");
  IElementType CONTINUE_STMT = new ZellElementType("CONTINUE_STMT");
  IElementType DEFAULT_CASE = new ZellElementType("DEFAULT_CASE");
  IElementType DEFER_STMT = new ZellElementType("DEFER_STMT");
  IElementType ELSE_VALUE = new ZellElementType("ELSE_VALUE");
  IElementType EMBED_DECL = new ZellElementType("EMBED_DECL");
  IElementType ENUM_DECL = ZellStubElementTypes.factory("ENUM_DECL");
  IElementType ENUM_VARIANT = new ZellElementType("ENUM_VARIANT");
  IElementType EOS = new ZellElementType("EOS");
  IElementType EXPR = new ZellElementType("EXPR");
  IElementType EXPR_STMT = new ZellElementType("EXPR_STMT");
  IElementType EXTERN_FUNC_DECL = new ZellElementType("EXTERN_FUNC_DECL");
  IElementType FIELD_DECL = new ZellElementType("FIELD_DECL");
  IElementType FIELD_INIT = new ZellElementType("FIELD_INIT");
  IElementType FIELD_NAME = new ZellElementType("FIELD_NAME");
  IElementType FOR_HEADER = new ZellElementType("FOR_HEADER");
  IElementType FOR_IN = new ZellElementType("FOR_IN");
  IElementType FOR_STMT = new ZellElementType("FOR_STMT");
  IElementType FUNC_DECL = ZellStubElementTypes.factory("FUNC_DECL");
  IElementType FUNC_SIGNATURE = new ZellElementType("FUNC_SIGNATURE");
  IElementType FUNC_TYPE = new ZellElementType("FUNC_TYPE");
  IElementType GENERIC_ARGS = new ZellElementType("GENERIC_ARGS");
  IElementType GENERIC_PARAM = new ZellElementType("GENERIC_PARAM");
  IElementType GENERIC_PARAMS = new ZellElementType("GENERIC_PARAMS");
  IElementType IF_STMT = new ZellElementType("IF_STMT");
  IElementType IMPLEMENTS_CLAUSE = new ZellElementType("IMPLEMENTS_CLAUSE");
  IElementType IMPORT_DECL = new ZellElementType("IMPORT_DECL");
  IElementType INTERFACE_DECL = ZellStubElementTypes.factory("INTERFACE_DECL");
  IElementType LAMBDA_EXPR = new ZellElementType("LAMBDA_EXPR");
  IElementType LAMBDA_NAMES = new ZellElementType("LAMBDA_NAMES");
  IElementType LITERAL = new ZellElementType("LITERAL");
  IElementType MAP_ENTRY = new ZellElementType("MAP_ENTRY");
  IElementType MAP_LITERAL = new ZellElementType("MAP_LITERAL");
  IElementType MAP_TYPE = new ZellElementType("MAP_TYPE");
  IElementType METHOD_DECL = new ZellElementType("METHOD_DECL");
  IElementType MUL_EXPR = new ZellElementType("MUL_EXPR");
  IElementType NAMED_TYPE = new ZellElementType("NAMED_TYPE");
  IElementType NAMESPACE_DECL = new ZellElementType("NAMESPACE_DECL");
  IElementType NAME_SEGMENT = new ZellElementType("NAME_SEGMENT");
  IElementType OR_EXPR = new ZellElementType("OR_EXPR");
  IElementType PARAM_GROUP = new ZellElementType("PARAM_GROUP");
  IElementType PARAM_LIST = new ZellElementType("PARAM_LIST");
  IElementType PATTERN = new ZellElementType("PATTERN");
  IElementType POINTER_TYPE = new ZellElementType("POINTER_TYPE");
  IElementType POSTFIX = new ZellElementType("POSTFIX");
  IElementType POSTFIX_EXPR = new ZellElementType("POSTFIX_EXPR");
  IElementType PREFIX_TYPE = new ZellElementType("PREFIX_TYPE");
  IElementType PRIMARY_EXPR = new ZellElementType("PRIMARY_EXPR");
  IElementType QUALIFIED_NAME = new ZellElementType("QUALIFIED_NAME");
  IElementType RANGE_EXPR = new ZellElementType("RANGE_EXPR");
  IElementType RANGE_PATTERN = new ZellElementType("RANGE_PATTERN");
  IElementType RETURN_STMT = new ZellElementType("RETURN_STMT");
  IElementType SLICE_LITERAL = new ZellElementType("SLICE_LITERAL");
  IElementType SLICE_TYPE = new ZellElementType("SLICE_TYPE");
  IElementType STATEMENT = new ZellElementType("STATEMENT");
  IElementType STRUCT_DECL = ZellStubElementTypes.factory("STRUCT_DECL");
  IElementType STRUCT_LITERAL = new ZellElementType("STRUCT_LITERAL");
  IElementType STRUCT_MEMBER = new ZellElementType("STRUCT_MEMBER");
  IElementType SWITCH_CASE = new ZellElementType("SWITCH_CASE");
  IElementType SWITCH_STMT = new ZellElementType("SWITCH_STMT");
  IElementType TOP_LEVEL_DECL = new ZellElementType("TOP_LEVEL_DECL");
  IElementType TRY_EXPR = new ZellElementType("TRY_EXPR");
  IElementType TRY_STMT = new ZellElementType("TRY_STMT");
  IElementType TUPLE_TYPE = new ZellElementType("TUPLE_TYPE");
  IElementType TYPE = new ZellElementType("TYPE");
  IElementType TYPE_ALIAS_DECL = new ZellElementType("TYPE_ALIAS_DECL");
  IElementType TYPE_BIND_PATTERN = new ZellElementType("TYPE_BIND_PATTERN");
  IElementType UNARY_EXPR = new ZellElementType("UNARY_EXPR");
  IElementType VAR_DECL = new ZellElementType("VAR_DECL");
  IElementType VAR_NAME = new ZellElementType("VAR_NAME");
  IElementType VAR_TUPLE = new ZellElementType("VAR_TUPLE");

  IElementType AMP = new ZellTokenType("&");
  IElementType AMPEQ = new ZellTokenType("&=");
  IElementType ANDAND = new ZellTokenType("&&");
  IElementType ARROW = new ZellTokenType("=>");
  IElementType AS = new ZellTokenType("as");
  IElementType ASSIGN = new ZellTokenType("=");
  IElementType ASYNC = new ZellTokenType("async");
  IElementType AT = new ZellTokenType("@");
  IElementType ATEQ = new ZellTokenType("@=");
  IElementType AWAIT = new ZellTokenType("await");
  IElementType BANG = new ZellTokenType("!");
  IElementType BLOCK_COMMENT = new ZellTokenType("BLOCK_COMMENT");
  IElementType BREAK = new ZellTokenType("break");
  IElementType CARET = new ZellTokenType("^");
  IElementType CARETEQ = new ZellTokenType("^=");
  IElementType CASE = new ZellTokenType("case");
  IElementType CATCH = new ZellTokenType("catch");
  IElementType CHAR_LITERAL = new ZellTokenType("CHAR_LITERAL");
  IElementType COLON = new ZellTokenType(":");
  IElementType COMMA = new ZellTokenType(",");
  IElementType CONST = new ZellTokenType("const");
  IElementType CONTINUE = new ZellTokenType("continue");
  IElementType DEFAULT = new ZellTokenType("default");
  IElementType DEFER = new ZellTokenType("defer");
  IElementType DOT = new ZellTokenType(".");
  IElementType DOTDOT = new ZellTokenType("..");
  IElementType ELSE = new ZellTokenType("else");
  IElementType ENUM = new ZellTokenType("enum");
  IElementType EQEQ = new ZellTokenType("==");
  IElementType FALSE = new ZellTokenType("false");
  IElementType FLOAT_LITERAL = new ZellTokenType("FLOAT_LITERAL");
  IElementType FOR = new ZellTokenType("for");
  IElementType FUNC = new ZellTokenType("func");
  IElementType GE = new ZellTokenType(">=");
  IElementType GT = new ZellTokenType(">");
  IElementType IDENTIFIER = new ZellTokenType("IDENTIFIER");
  IElementType IF = new ZellTokenType("if");
  IElementType IMPORT = new ZellTokenType("import");
  IElementType INTERFACE = new ZellTokenType("interface");
  IElementType INT_LITERAL = new ZellTokenType("INT_LITERAL");
  IElementType LBRACE = new ZellTokenType("{");
  IElementType LBRACKET = new ZellTokenType("[");
  IElementType LE = new ZellTokenType("<=");
  IElementType LINE_COMMENT = new ZellTokenType("LINE_COMMENT");
  IElementType LPAREN = new ZellTokenType("(");
  IElementType LT = new ZellTokenType("<");
  IElementType MAP = new ZellTokenType("map");
  IElementType MINUS = new ZellTokenType("-");
  IElementType MINUSEQ = new ZellTokenType("-=");
  IElementType NAMESPACE = new ZellTokenType("namespace");
  IElementType NEW = new ZellTokenType("new");
  IElementType NIL = new ZellTokenType("nil");
  IElementType NOTEQ = new ZellTokenType("!=");
  IElementType OROR = new ZellTokenType("||");
  IElementType PERCENT = new ZellTokenType("%");
  IElementType PERCENTEQ = new ZellTokenType("%=");
  IElementType PIPE = new ZellTokenType("|");
  IElementType PIPEEQ = new ZellTokenType("|=");
  IElementType PLUS = new ZellTokenType("+");
  IElementType PLUSEQ = new ZellTokenType("+=");
  IElementType QUESTION = new ZellTokenType("?");
  IElementType RAW_STRING_LITERAL = new ZellTokenType("RAW_STRING_LITERAL");
  IElementType RBRACE = new ZellTokenType("}");
  IElementType RBRACKET = new ZellTokenType("]");
  IElementType RETURN = new ZellTokenType("return");
  IElementType RPAREN = new ZellTokenType(")");
  IElementType SEMI = new ZellTokenType(";");
  IElementType SHL = new ZellTokenType("<<");
  IElementType SHLEQ = new ZellTokenType("<<=");
  IElementType SHR = new ZellTokenType(">>");
  IElementType SHREQ = new ZellTokenType(">>=");
  IElementType SLASH = new ZellTokenType("/");
  IElementType SLASHEQ = new ZellTokenType("/=");
  IElementType STAR = new ZellTokenType("*");
  IElementType STAREQ = new ZellTokenType("*=");
  IElementType STATIC = new ZellTokenType("static");
  IElementType STRING_LITERAL = new ZellTokenType("STRING_LITERAL");
  IElementType STRUCT = new ZellTokenType("struct");
  IElementType SWITCH = new ZellTokenType("switch");
  IElementType THIS = new ZellTokenType("this");
  IElementType TRUE = new ZellTokenType("true");
  IElementType TRY = new ZellTokenType("try");
  IElementType UNDERSCORE = new ZellTokenType("_");
  IElementType VAR = new ZellTokenType("var");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADD_EXPR) {
        return new ZellAddExprImpl(node);
      }
      else if (type == AND_EXPR) {
        return new ZellAndExprImpl(node);
      }
      else if (type == ARG_LIST) {
        return new ZellArgListImpl(node);
      }
      else if (type == ARRAY_TYPE) {
        return new ZellArrayTypeImpl(node);
      }
      else if (type == ASSIGN_OP) {
        return new ZellAssignOpImpl(node);
      }
      else if (type == ASSIGN_STMT) {
        return new ZellAssignStmtImpl(node);
      }
      else if (type == ATTRIBUTE) {
        return new ZellAttributeImpl(node);
      }
      else if (type == ATTRIBUTE_ARG) {
        return new ZellAttributeArgImpl(node);
      }
      else if (type == ATTRIBUTE_ARGS) {
        return new ZellAttributeArgsImpl(node);
      }
      else if (type == BLOCK) {
        return new ZellBlockImpl(node);
      }
      else if (type == BREAK_STMT) {
        return new ZellBreakStmtImpl(node);
      }
      else if (type == CALL_ARG) {
        return new ZellCallArgImpl(node);
      }
      else if (type == CMP_EXPR) {
        return new ZellCmpExprImpl(node);
      }
      else if (type == CONST_DECL) {
        return new ZellConstDeclImpl(node);
      }
      else if (type == CONTINUE_STMT) {
        return new ZellContinueStmtImpl(node);
      }
      else if (type == DEFAULT_CASE) {
        return new ZellDefaultCaseImpl(node);
      }
      else if (type == DEFER_STMT) {
        return new ZellDeferStmtImpl(node);
      }
      else if (type == ELSE_VALUE) {
        return new ZellElseValueImpl(node);
      }
      else if (type == EMBED_DECL) {
        return new ZellEmbedDeclImpl(node);
      }
      else if (type == ENUM_DECL) {
        return new ZellEnumDeclImpl(node);
      }
      else if (type == ENUM_VARIANT) {
        return new ZellEnumVariantImpl(node);
      }
      else if (type == EOS) {
        return new ZellEosImpl(node);
      }
      else if (type == EXPR) {
        return new ZellExprImpl(node);
      }
      else if (type == EXPR_STMT) {
        return new ZellExprStmtImpl(node);
      }
      else if (type == EXTERN_FUNC_DECL) {
        return new ZellExternFuncDeclImpl(node);
      }
      else if (type == FIELD_DECL) {
        return new ZellFieldDeclImpl(node);
      }
      else if (type == FIELD_INIT) {
        return new ZellFieldInitImpl(node);
      }
      else if (type == FIELD_NAME) {
        return new ZellFieldNameImpl(node);
      }
      else if (type == FOR_HEADER) {
        return new ZellForHeaderImpl(node);
      }
      else if (type == FOR_IN) {
        return new ZellForInImpl(node);
      }
      else if (type == FOR_STMT) {
        return new ZellForStmtImpl(node);
      }
      else if (type == FUNC_DECL) {
        return new ZellFuncDeclImpl(node);
      }
      else if (type == FUNC_SIGNATURE) {
        return new ZellFuncSignatureImpl(node);
      }
      else if (type == FUNC_TYPE) {
        return new ZellFuncTypeImpl(node);
      }
      else if (type == GENERIC_ARGS) {
        return new ZellGenericArgsImpl(node);
      }
      else if (type == GENERIC_PARAM) {
        return new ZellGenericParamImpl(node);
      }
      else if (type == GENERIC_PARAMS) {
        return new ZellGenericParamsImpl(node);
      }
      else if (type == IF_STMT) {
        return new ZellIfStmtImpl(node);
      }
      else if (type == IMPLEMENTS_CLAUSE) {
        return new ZellImplementsClauseImpl(node);
      }
      else if (type == IMPORT_DECL) {
        return new ZellImportDeclImpl(node);
      }
      else if (type == INTERFACE_DECL) {
        return new ZellInterfaceDeclImpl(node);
      }
      else if (type == LAMBDA_EXPR) {
        return new ZellLambdaExprImpl(node);
      }
      else if (type == LAMBDA_NAMES) {
        return new ZellLambdaNamesImpl(node);
      }
      else if (type == LITERAL) {
        return new ZellLiteralImpl(node);
      }
      else if (type == MAP_ENTRY) {
        return new ZellMapEntryImpl(node);
      }
      else if (type == MAP_LITERAL) {
        return new ZellMapLiteralImpl(node);
      }
      else if (type == MAP_TYPE) {
        return new ZellMapTypeImpl(node);
      }
      else if (type == METHOD_DECL) {
        return new ZellMethodDeclImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new ZellMulExprImpl(node);
      }
      else if (type == NAMED_TYPE) {
        return new ZellNamedTypeImpl(node);
      }
      else if (type == NAMESPACE_DECL) {
        return new ZellNamespaceDeclImpl(node);
      }
      else if (type == NAME_SEGMENT) {
        return new ZellNameSegmentImpl(node);
      }
      else if (type == OR_EXPR) {
        return new ZellOrExprImpl(node);
      }
      else if (type == PARAM_GROUP) {
        return new ZellParamGroupImpl(node);
      }
      else if (type == PARAM_LIST) {
        return new ZellParamListImpl(node);
      }
      else if (type == PATTERN) {
        return new ZellPatternImpl(node);
      }
      else if (type == POINTER_TYPE) {
        return new ZellPointerTypeImpl(node);
      }
      else if (type == POSTFIX) {
        return new ZellPostfixImpl(node);
      }
      else if (type == POSTFIX_EXPR) {
        return new ZellPostfixExprImpl(node);
      }
      else if (type == PREFIX_TYPE) {
        return new ZellPrefixTypeImpl(node);
      }
      else if (type == PRIMARY_EXPR) {
        return new ZellPrimaryExprImpl(node);
      }
      else if (type == QUALIFIED_NAME) {
        return new ZellQualifiedNameImpl(node);
      }
      else if (type == RANGE_EXPR) {
        return new ZellRangeExprImpl(node);
      }
      else if (type == RANGE_PATTERN) {
        return new ZellRangePatternImpl(node);
      }
      else if (type == RETURN_STMT) {
        return new ZellReturnStmtImpl(node);
      }
      else if (type == SLICE_LITERAL) {
        return new ZellSliceLiteralImpl(node);
      }
      else if (type == SLICE_TYPE) {
        return new ZellSliceTypeImpl(node);
      }
      else if (type == STATEMENT) {
        return new ZellStatementImpl(node);
      }
      else if (type == STRUCT_DECL) {
        return new ZellStructDeclImpl(node);
      }
      else if (type == STRUCT_LITERAL) {
        return new ZellStructLiteralImpl(node);
      }
      else if (type == STRUCT_MEMBER) {
        return new ZellStructMemberImpl(node);
      }
      else if (type == SWITCH_CASE) {
        return new ZellSwitchCaseImpl(node);
      }
      else if (type == SWITCH_STMT) {
        return new ZellSwitchStmtImpl(node);
      }
      else if (type == TOP_LEVEL_DECL) {
        return new ZellTopLevelDeclImpl(node);
      }
      else if (type == TRY_EXPR) {
        return new ZellTryExprImpl(node);
      }
      else if (type == TRY_STMT) {
        return new ZellTryStmtImpl(node);
      }
      else if (type == TUPLE_TYPE) {
        return new ZellTupleTypeImpl(node);
      }
      else if (type == TYPE) {
        return new ZellTypeImpl(node);
      }
      else if (type == TYPE_ALIAS_DECL) {
        return new ZellTypeAliasDeclImpl(node);
      }
      else if (type == TYPE_BIND_PATTERN) {
        return new ZellTypeBindPatternImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new ZellUnaryExprImpl(node);
      }
      else if (type == VAR_DECL) {
        return new ZellVarDeclImpl(node);
      }
      else if (type == VAR_NAME) {
        return new ZellVarNameImpl(node);
      }
      else if (type == VAR_TUPLE) {
        return new ZellVarTupleImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
