// This is a generated file. Not intended for manual editing.
package org.zell.idea.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.zell.idea.psi.ZellTypes.*;
import static org.zell.idea.parser.ZellParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ZellParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return file(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // mul_expr ((PLUS | MINUS | PIPE | CARET) mul_expr)*
  public static boolean add_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADD_EXPR, "<add expr>");
    result_ = mul_expr(builder_, level_ + 1);
    result_ = result_ && add_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ((PLUS | MINUS | PIPE | CARET) mul_expr)*
  private static boolean add_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!add_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "add_expr_1", pos_)) break;
    }
    return true;
  }

  // (PLUS | MINUS | PIPE | CARET) mul_expr
  private static boolean add_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = add_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && mul_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PLUS | MINUS | PIPE | CARET
  private static boolean add_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr_1_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, PLUS);
    if (!result_) result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, PIPE);
    if (!result_) result_ = consumeToken(builder_, CARET);
    return result_;
  }

  /* ********************************************************** */
  // cmp_expr (ANDAND cmp_expr)*
  public static boolean and_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "and_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, AND_EXPR, "<and expr>");
    result_ = cmp_expr(builder_, level_ + 1);
    result_ = result_ && and_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (ANDAND cmp_expr)*
  private static boolean and_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "and_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!and_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "and_expr_1", pos_)) break;
    }
    return true;
  }

  // ANDAND cmp_expr
  private static boolean and_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "and_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ANDAND);
    result_ = result_ && cmp_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // call_arg (COMMA call_arg)* COMMA?
  public static boolean arg_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_LIST, "<arg list>");
    result_ = call_arg(builder_, level_ + 1);
    result_ = result_ && arg_list_1(builder_, level_ + 1);
    result_ = result_ && arg_list_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COMMA call_arg)*
  private static boolean arg_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!arg_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "arg_list_1", pos_)) break;
    }
    return true;
  }

  // COMMA call_arg
  private static boolean arg_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && call_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA?
  private static boolean arg_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // LBRACKET expr RBRACKET prefix_type
  public static boolean array_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_type")) return false;
    if (!nextTokenIs(builder_, LBRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACKET);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACKET);
    result_ = result_ && prefix_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, ARRAY_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // ASSIGN | PLUSEQ | MINUSEQ | STAREQ | SLASHEQ | PERCENTEQ | AMPEQ | PIPEEQ | CARETEQ | SHLEQ | SHREQ | ATEQ
  public static boolean assign_op(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assign_op")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGN_OP, "<assign op>");
    result_ = consumeToken(builder_, ASSIGN);
    if (!result_) result_ = consumeToken(builder_, PLUSEQ);
    if (!result_) result_ = consumeToken(builder_, MINUSEQ);
    if (!result_) result_ = consumeToken(builder_, STAREQ);
    if (!result_) result_ = consumeToken(builder_, SLASHEQ);
    if (!result_) result_ = consumeToken(builder_, PERCENTEQ);
    if (!result_) result_ = consumeToken(builder_, AMPEQ);
    if (!result_) result_ = consumeToken(builder_, PIPEEQ);
    if (!result_) result_ = consumeToken(builder_, CARETEQ);
    if (!result_) result_ = consumeToken(builder_, SHLEQ);
    if (!result_) result_ = consumeToken(builder_, SHREQ);
    if (!result_) result_ = consumeToken(builder_, ATEQ);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // expr (COMMA expr)* assign_op expr (COMMA expr)*
  public static boolean assign_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assign_stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGN_STMT, "<assign stmt>");
    result_ = expr(builder_, level_ + 1);
    result_ = result_ && assign_stmt_1(builder_, level_ + 1);
    result_ = result_ && assign_op(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && assign_stmt_4(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COMMA expr)*
  private static boolean assign_stmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assign_stmt_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!assign_stmt_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "assign_stmt_1", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean assign_stmt_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assign_stmt_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA expr)*
  private static boolean assign_stmt_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assign_stmt_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!assign_stmt_4_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "assign_stmt_4", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean assign_stmt_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assign_stmt_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AT qualified_name attribute_args? eos?
  public static boolean attribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute")) return false;
    if (!nextTokenIs(builder_, AT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AT);
    result_ = result_ && qualified_name(builder_, level_ + 1);
    result_ = result_ && attribute_2(builder_, level_ + 1);
    result_ = result_ && attribute_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, ATTRIBUTE, result_);
    return result_;
  }

  // attribute_args?
  private static boolean attribute_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_2")) return false;
    attribute_args(builder_, level_ + 1);
    return true;
  }

  // eos?
  private static boolean attribute_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_3")) return false;
    eos(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (field_name COLON)? expr
  public static boolean attribute_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ATTRIBUTE_ARG, "<attribute arg>");
    result_ = attribute_arg_0(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (field_name COLON)?
  private static boolean attribute_arg_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg_0")) return false;
    attribute_arg_0_0(builder_, level_ + 1);
    return true;
  }

  // field_name COLON
  private static boolean attribute_arg_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_arg_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = field_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAREN (attribute_arg (COMMA attribute_arg)*)? RPAREN
  public static boolean attribute_args(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_args")) return false;
    if (!nextTokenIs(builder_, LPAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && attribute_args_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, ATTRIBUTE_ARGS, result_);
    return result_;
  }

  // (attribute_arg (COMMA attribute_arg)*)?
  private static boolean attribute_args_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_args_1")) return false;
    attribute_args_1_0(builder_, level_ + 1);
    return true;
  }

  // attribute_arg (COMMA attribute_arg)*
  private static boolean attribute_args_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_args_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = attribute_arg(builder_, level_ + 1);
    result_ = result_ && attribute_args_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA attribute_arg)*
  private static boolean attribute_args_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_args_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute_args_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "attribute_args_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA attribute_arg
  private static boolean attribute_args_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "attribute_args_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && attribute_arg(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACE statement* RBRACE
  public static boolean block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block")) return false;
    if (!nextTokenIs(builder_, LBRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACE);
    result_ = result_ && block_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, BLOCK, result_);
    return result_;
  }

  // statement*
  private static boolean block_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "block_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // BREAK
  public static boolean break_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "break_stmt")) return false;
    if (!nextTokenIs(builder_, BREAK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BREAK);
    exit_section_(builder_, marker_, BREAK_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // (field_name COLON)? expr
  public static boolean call_arg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_arg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CALL_ARG, "<call arg>");
    result_ = call_arg_0(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (field_name COLON)?
  private static boolean call_arg_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_arg_0")) return false;
    call_arg_0_0(builder_, level_ + 1);
    return true;
  }

  // field_name COLON
  private static boolean call_arg_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_arg_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = field_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // add_expr ((EQEQ | NOTEQ | LT | LE | GT | GE) add_expr)*
  public static boolean cmp_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "cmp_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CMP_EXPR, "<cmp expr>");
    result_ = add_expr(builder_, level_ + 1);
    result_ = result_ && cmp_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ((EQEQ | NOTEQ | LT | LE | GT | GE) add_expr)*
  private static boolean cmp_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "cmp_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!cmp_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "cmp_expr_1", pos_)) break;
    }
    return true;
  }

  // (EQEQ | NOTEQ | LT | LE | GT | GE) add_expr
  private static boolean cmp_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "cmp_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = cmp_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && add_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // EQEQ | NOTEQ | LT | LE | GT | GE
  private static boolean cmp_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "cmp_expr_1_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, EQEQ);
    if (!result_) result_ = consumeToken(builder_, NOTEQ);
    if (!result_) result_ = consumeToken(builder_, LT);
    if (!result_) result_ = consumeToken(builder_, LE);
    if (!result_) result_ = consumeToken(builder_, GT);
    if (!result_) result_ = consumeToken(builder_, GE);
    return result_;
  }

  /* ********************************************************** */
  // CONST IDENTIFIER type? ASSIGN expr
  public static boolean const_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "const_decl")) return false;
    if (!nextTokenIs(builder_, CONST)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, CONST, IDENTIFIER);
    result_ = result_ && const_decl_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ASSIGN);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, CONST_DECL, result_);
    return result_;
  }

  // type?
  private static boolean const_decl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "const_decl_2")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // CONTINUE
  public static boolean continue_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "continue_stmt")) return false;
    if (!nextTokenIs(builder_, CONTINUE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONTINUE);
    exit_section_(builder_, marker_, CONTINUE_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // DEFAULT COLON statement*
  public static boolean default_case(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "default_case")) return false;
    if (!nextTokenIs(builder_, DEFAULT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, DEFAULT, COLON);
    result_ = result_ && default_case_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, DEFAULT_CASE, result_);
    return result_;
  }

  // statement*
  private static boolean default_case_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "default_case_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "default_case_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DEFER expr
  public static boolean defer_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "defer_stmt")) return false;
    if (!nextTokenIs(builder_, DEFER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DEFER);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, DEFER_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // CONTINUE | BREAK | return_stmt | expr
  public static boolean else_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "else_value")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ELSE_VALUE, "<else value>");
    result_ = consumeToken(builder_, CONTINUE);
    if (!result_) result_ = consumeToken(builder_, BREAK);
    if (!result_) result_ = return_stmt(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // type
  public static boolean embed_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "embed_decl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EMBED_DECL, "<embed decl>");
    result_ = type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ENUM IDENTIFIER generic_params? implements_clause? LBRACE enum_variant ((COMMA | eos) enum_variant)* (COMMA | eos)?
  //               (attribute* method_decl eos?)* RBRACE
  public static boolean enum_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl")) return false;
    if (!nextTokenIs(builder_, ENUM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ENUM, IDENTIFIER);
    result_ = result_ && enum_decl_2(builder_, level_ + 1);
    result_ = result_ && enum_decl_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && enum_variant(builder_, level_ + 1);
    result_ = result_ && enum_decl_6(builder_, level_ + 1);
    result_ = result_ && enum_decl_7(builder_, level_ + 1);
    result_ = result_ && enum_decl_8(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, ENUM_DECL, result_);
    return result_;
  }

  // generic_params?
  private static boolean enum_decl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_2")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // implements_clause?
  private static boolean enum_decl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_3")) return false;
    implements_clause(builder_, level_ + 1);
    return true;
  }

  // ((COMMA | eos) enum_variant)*
  private static boolean enum_decl_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_6")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!enum_decl_6_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "enum_decl_6", pos_)) break;
    }
    return true;
  }

  // (COMMA | eos) enum_variant
  private static boolean enum_decl_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = enum_decl_6_0_0(builder_, level_ + 1);
    result_ = result_ && enum_variant(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA | eos
  private static boolean enum_decl_6_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_6_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = eos(builder_, level_ + 1);
    return result_;
  }

  // (COMMA | eos)?
  private static boolean enum_decl_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_7")) return false;
    enum_decl_7_0(builder_, level_ + 1);
    return true;
  }

  // COMMA | eos
  private static boolean enum_decl_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_7_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = eos(builder_, level_ + 1);
    return result_;
  }

  // (attribute* method_decl eos?)*
  private static boolean enum_decl_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_8")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!enum_decl_8_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "enum_decl_8", pos_)) break;
    }
    return true;
  }

  // attribute* method_decl eos?
  private static boolean enum_decl_8_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_8_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = enum_decl_8_0_0(builder_, level_ + 1);
    result_ = result_ && method_decl(builder_, level_ + 1);
    result_ = result_ && enum_decl_8_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // attribute*
  private static boolean enum_decl_8_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_8_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "enum_decl_8_0_0", pos_)) break;
    }
    return true;
  }

  // eos?
  private static boolean enum_decl_8_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_decl_8_0_2")) return false;
    eos(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // attribute* IDENTIFIER (LPAREN param_list? RPAREN)?
  public static boolean enum_variant(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_variant")) return false;
    if (!nextTokenIs(builder_, "<enum variant>", AT, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_VARIANT, "<enum variant>");
    result_ = enum_variant_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && enum_variant_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // attribute*
  private static boolean enum_variant_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_variant_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "enum_variant_0", pos_)) break;
    }
    return true;
  }

  // (LPAREN param_list? RPAREN)?
  private static boolean enum_variant_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_variant_2")) return false;
    enum_variant_2_0(builder_, level_ + 1);
    return true;
  }

  // LPAREN param_list? RPAREN
  private static boolean enum_variant_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_variant_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && enum_variant_2_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // param_list?
  private static boolean enum_variant_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "enum_variant_2_0_1")) return false;
    param_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // SEMI
  public static boolean eos(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "eos")) return false;
    if (!nextTokenIs(builder_, SEMI)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEMI);
    exit_section_(builder_, marker_, EOS, result_);
    return result_;
  }

  /* ********************************************************** */
  // try_expr | or_expr
  public static boolean expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXPR, "<expr>");
    result_ = try_expr(builder_, level_ + 1);
    if (!result_) result_ = or_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // expr
  public static boolean expr_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expr_stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXPR_STMT, "<expr stmt>");
    result_ = expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER func_signature
  public static boolean extern_func_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "extern_func_decl")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && func_signature(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXTERN_FUNC_DECL, result_);
    return result_;
  }

  /* ********************************************************** */
  // field_name (COMMA field_name)* type (ASSIGN expr)?
  public static boolean field_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_decl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FIELD_DECL, "<field decl>");
    result_ = field_name(builder_, level_ + 1);
    result_ = result_ && field_decl_1(builder_, level_ + 1);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && field_decl_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COMMA field_name)*
  private static boolean field_decl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_decl_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!field_decl_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "field_decl_1", pos_)) break;
    }
    return true;
  }

  // COMMA field_name
  private static boolean field_decl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_decl_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && field_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ASSIGN expr)?
  private static boolean field_decl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_decl_3")) return false;
    field_decl_3_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN expr
  private static boolean field_decl_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_decl_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // field_name COLON expr
  public static boolean field_init(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_init")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FIELD_INIT, "<field init>");
    result_ = field_name(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER | NAMESPACE | IMPORT | AS | STRUCT | INTERFACE | ENUM | AWAIT | VAR | CONST | IF | ELSE | FOR | SWITCH | CASE | DEFAULT | RETURN | BREAK | CONTINUE | DEFER | TRY | CATCH | NEW | NIL | TRUE | FALSE | MAP | THIS
  public static boolean field_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "field_name")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FIELD_NAME, "<field name>");
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, NAMESPACE);
    if (!result_) result_ = consumeToken(builder_, IMPORT);
    if (!result_) result_ = consumeToken(builder_, AS);
    if (!result_) result_ = consumeToken(builder_, STRUCT);
    if (!result_) result_ = consumeToken(builder_, INTERFACE);
    if (!result_) result_ = consumeToken(builder_, ENUM);
    if (!result_) result_ = consumeToken(builder_, AWAIT);
    if (!result_) result_ = consumeToken(builder_, VAR);
    if (!result_) result_ = consumeToken(builder_, CONST);
    if (!result_) result_ = consumeToken(builder_, IF);
    if (!result_) result_ = consumeToken(builder_, ELSE);
    if (!result_) result_ = consumeToken(builder_, FOR);
    if (!result_) result_ = consumeToken(builder_, SWITCH);
    if (!result_) result_ = consumeToken(builder_, CASE);
    if (!result_) result_ = consumeToken(builder_, DEFAULT);
    if (!result_) result_ = consumeToken(builder_, RETURN);
    if (!result_) result_ = consumeToken(builder_, BREAK);
    if (!result_) result_ = consumeToken(builder_, CONTINUE);
    if (!result_) result_ = consumeToken(builder_, DEFER);
    if (!result_) result_ = consumeToken(builder_, TRY);
    if (!result_) result_ = consumeToken(builder_, CATCH);
    if (!result_) result_ = consumeToken(builder_, NEW);
    if (!result_) result_ = consumeToken(builder_, NIL);
    if (!result_) result_ = consumeToken(builder_, TRUE);
    if (!result_) result_ = consumeToken(builder_, FALSE);
    if (!result_) result_ = consumeToken(builder_, MAP);
    if (!result_) result_ = consumeToken(builder_, THIS);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // attribute* namespace_decl import_decl* top_level_decl*
  static boolean file(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file")) return false;
    if (!nextTokenIs(builder_, "", AT, NAMESPACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = file_0(builder_, level_ + 1);
    result_ = result_ && namespace_decl(builder_, level_ + 1);
    result_ = result_ && file_2(builder_, level_ + 1);
    result_ = result_ && file_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // attribute*
  private static boolean file_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "file_0", pos_)) break;
    }
    return true;
  }

  // import_decl*
  private static boolean file_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!import_decl(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "file_2", pos_)) break;
    }
    return true;
  }

  // top_level_decl*
  private static boolean file_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "file_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!top_level_decl(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "file_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // for_in | expr
  public static boolean for_header(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_header")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_HEADER, "<for header>");
    result_ = for_in(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // var_name (COMMA var_name)? IDENTIFIER (range_expr | expr)
  public static boolean for_in(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_in")) return false;
    if (!nextTokenIs(builder_, "<for in>", IDENTIFIER, UNDERSCORE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_IN, "<for in>");
    result_ = var_name(builder_, level_ + 1);
    result_ = result_ && for_in_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && for_in_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COMMA var_name)?
  private static boolean for_in_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_in_1")) return false;
    for_in_1_0(builder_, level_ + 1);
    return true;
  }

  // COMMA var_name
  private static boolean for_in_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_in_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && var_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // range_expr | expr
  private static boolean for_in_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_in_3")) return false;
    boolean result_;
    result_ = range_expr(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // FOR for_header? block
  public static boolean for_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_stmt")) return false;
    if (!nextTokenIs(builder_, FOR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FOR);
    result_ = result_ && for_stmt_1(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_STMT, result_);
    return result_;
  }

  // for_header?
  private static boolean for_stmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_stmt_1")) return false;
    for_header(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // func_signature block
  public static boolean func_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_decl")) return false;
    if (!nextTokenIs(builder_, "<func decl>", ASYNC, FUNC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNC_DECL, "<func decl>");
    result_ = func_signature(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ASYNC? FUNC IDENTIFIER generic_params? LPAREN param_list? RPAREN type?
  public static boolean func_signature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_signature")) return false;
    if (!nextTokenIs(builder_, "<func signature>", ASYNC, FUNC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNC_SIGNATURE, "<func signature>");
    result_ = func_signature_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, FUNC, IDENTIFIER);
    result_ = result_ && func_signature_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LPAREN);
    result_ = result_ && func_signature_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    result_ = result_ && func_signature_7(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ASYNC?
  private static boolean func_signature_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_signature_0")) return false;
    consumeToken(builder_, ASYNC);
    return true;
  }

  // generic_params?
  private static boolean func_signature_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_signature_3")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // param_list?
  private static boolean func_signature_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_signature_5")) return false;
    param_list(builder_, level_ + 1);
    return true;
  }

  // type?
  private static boolean func_signature_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_signature_7")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // FUNC LPAREN (type (COMMA type)*)? RPAREN type?
  public static boolean func_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_type")) return false;
    if (!nextTokenIs(builder_, FUNC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, FUNC, LPAREN);
    result_ = result_ && func_type_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    result_ = result_ && func_type_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, FUNC_TYPE, result_);
    return result_;
  }

  // (type (COMMA type)*)?
  private static boolean func_type_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_type_2")) return false;
    func_type_2_0(builder_, level_ + 1);
    return true;
  }

  // type (COMMA type)*
  private static boolean func_type_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_type_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = type(builder_, level_ + 1);
    result_ = result_ && func_type_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA type)*
  private static boolean func_type_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_type_2_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!func_type_2_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "func_type_2_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA type
  private static boolean func_type_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_type_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // type?
  private static boolean func_type_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_type_4")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LT type (COMMA type)* GT
  public static boolean generic_args(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_args")) return false;
    if (!nextTokenIs(builder_, LT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LT);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && generic_args_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GT);
    exit_section_(builder_, marker_, GENERIC_ARGS, result_);
    return result_;
  }

  // (COMMA type)*
  private static boolean generic_args_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_args_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!generic_args_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_args_2", pos_)) break;
    }
    return true;
  }

  // COMMA type
  private static boolean generic_args_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_args_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER (COLON type)?
  public static boolean generic_param(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && generic_param_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, GENERIC_PARAM, result_);
    return result_;
  }

  // (COLON type)?
  private static boolean generic_param_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1")) return false;
    generic_param_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON type
  private static boolean generic_param_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_param_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LT generic_param (COMMA generic_param)* GT
  public static boolean generic_params(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_params")) return false;
    if (!nextTokenIs(builder_, LT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LT);
    result_ = result_ && generic_param(builder_, level_ + 1);
    result_ = result_ && generic_params_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GT);
    exit_section_(builder_, marker_, GENERIC_PARAMS, result_);
    return result_;
  }

  // (COMMA generic_param)*
  private static boolean generic_params_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_params_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!generic_params_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "generic_params_2", pos_)) break;
    }
    return true;
  }

  // COMMA generic_param
  private static boolean generic_params_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "generic_params_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && generic_param(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IF expr block (ELSE (if_stmt | block))?
  public static boolean if_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt")) return false;
    if (!nextTokenIs(builder_, IF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IF);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && if_stmt_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_STMT, result_);
    return result_;
  }

  // (ELSE (if_stmt | block))?
  private static boolean if_stmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_3")) return false;
    if_stmt_3_0(builder_, level_ + 1);
    return true;
  }

  // ELSE (if_stmt | block)
  private static boolean if_stmt_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE);
    result_ = result_ && if_stmt_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // if_stmt | block
  private static boolean if_stmt_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_3_0_1")) return false;
    boolean result_;
    result_ = if_stmt(builder_, level_ + 1);
    if (!result_) result_ = block(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER type (COMMA type)*
  public static boolean implements_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "implements_clause")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && implements_clause_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, IMPLEMENTS_CLAUSE, result_);
    return result_;
  }

  // (COMMA type)*
  private static boolean implements_clause_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "implements_clause_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!implements_clause_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "implements_clause_2", pos_)) break;
    }
    return true;
  }

  // COMMA type
  private static boolean implements_clause_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "implements_clause_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IMPORT qualified_name (AS IDENTIFIER)? eos
  public static boolean import_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_decl")) return false;
    if (!nextTokenIs(builder_, IMPORT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IMPORT);
    result_ = result_ && qualified_name(builder_, level_ + 1);
    result_ = result_ && import_decl_2(builder_, level_ + 1);
    result_ = result_ && eos(builder_, level_ + 1);
    exit_section_(builder_, marker_, IMPORT_DECL, result_);
    return result_;
  }

  // (AS IDENTIFIER)?
  private static boolean import_decl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_decl_2")) return false;
    import_decl_2_0(builder_, level_ + 1);
    return true;
  }

  // AS IDENTIFIER
  private static boolean import_decl_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_decl_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, AS, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER? INTERFACE IDENTIFIER generic_params? LBRACE ((attribute* func_signature block? | named_type) eos?)* RBRACE
  public static boolean interface_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl")) return false;
    if (!nextTokenIs(builder_, "<interface decl>", IDENTIFIER, INTERFACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INTERFACE_DECL, "<interface decl>");
    result_ = interface_decl_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, INTERFACE, IDENTIFIER);
    result_ = result_ && interface_decl_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && interface_decl_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENTIFIER?
  private static boolean interface_decl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_0")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  // generic_params?
  private static boolean interface_decl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_3")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // ((attribute* func_signature block? | named_type) eos?)*
  private static boolean interface_decl_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!interface_decl_5_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "interface_decl_5", pos_)) break;
    }
    return true;
  }

  // (attribute* func_signature block? | named_type) eos?
  private static boolean interface_decl_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = interface_decl_5_0_0(builder_, level_ + 1);
    result_ = result_ && interface_decl_5_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // attribute* func_signature block? | named_type
  private static boolean interface_decl_5_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = interface_decl_5_0_0_0(builder_, level_ + 1);
    if (!result_) result_ = named_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // attribute* func_signature block?
  private static boolean interface_decl_5_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5_0_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = interface_decl_5_0_0_0_0(builder_, level_ + 1);
    result_ = result_ && func_signature(builder_, level_ + 1);
    result_ = result_ && interface_decl_5_0_0_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // attribute*
  private static boolean interface_decl_5_0_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5_0_0_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "interface_decl_5_0_0_0_0", pos_)) break;
    }
    return true;
  }

  // block?
  private static boolean interface_decl_5_0_0_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5_0_0_0_2")) return false;
    block(builder_, level_ + 1);
    return true;
  }

  // eos?
  private static boolean interface_decl_5_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "interface_decl_5_0_1")) return false;
    eos(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ASYNC? FUNC LPAREN param_list? RPAREN type? block
  //               | ASYNC? LPAREN (param_list | lambda_names)? RPAREN ARROW (block | expr)
  public static boolean lambda_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA_EXPR, "<lambda expr>");
    result_ = lambda_expr_0(builder_, level_ + 1);
    if (!result_) result_ = lambda_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ASYNC? FUNC LPAREN param_list? RPAREN type? block
  private static boolean lambda_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = lambda_expr_0_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, FUNC, LPAREN);
    result_ = result_ && lambda_expr_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    result_ = result_ && lambda_expr_0_5(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ASYNC?
  private static boolean lambda_expr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_0_0")) return false;
    consumeToken(builder_, ASYNC);
    return true;
  }

  // param_list?
  private static boolean lambda_expr_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_0_3")) return false;
    param_list(builder_, level_ + 1);
    return true;
  }

  // type?
  private static boolean lambda_expr_0_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_0_5")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  // ASYNC? LPAREN (param_list | lambda_names)? RPAREN ARROW (block | expr)
  private static boolean lambda_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = lambda_expr_1_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LPAREN);
    result_ = result_ && lambda_expr_1_2(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, RPAREN, ARROW);
    result_ = result_ && lambda_expr_1_5(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ASYNC?
  private static boolean lambda_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_1_0")) return false;
    consumeToken(builder_, ASYNC);
    return true;
  }

  // (param_list | lambda_names)?
  private static boolean lambda_expr_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_1_2")) return false;
    lambda_expr_1_2_0(builder_, level_ + 1);
    return true;
  }

  // param_list | lambda_names
  private static boolean lambda_expr_1_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_1_2_0")) return false;
    boolean result_;
    result_ = param_list(builder_, level_ + 1);
    if (!result_) result_ = lambda_names(builder_, level_ + 1);
    return result_;
  }

  // block | expr
  private static boolean lambda_expr_1_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_expr_1_5")) return false;
    boolean result_;
    result_ = block(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER (COMMA IDENTIFIER)*
  public static boolean lambda_names(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_names")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && lambda_names_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, LAMBDA_NAMES, result_);
    return result_;
  }

  // (COMMA IDENTIFIER)*
  private static boolean lambda_names_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_names_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!lambda_names_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "lambda_names_1", pos_)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean lambda_names_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda_names_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // INT_LITERAL | FLOAT_LITERAL | STRING_LITERAL | RAW_STRING_LITERAL | CHAR_LITERAL | TRUE | FALSE
  public static boolean literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL, "<literal>");
    result_ = consumeToken(builder_, INT_LITERAL);
    if (!result_) result_ = consumeToken(builder_, FLOAT_LITERAL);
    if (!result_) result_ = consumeToken(builder_, STRING_LITERAL);
    if (!result_) result_ = consumeToken(builder_, RAW_STRING_LITERAL);
    if (!result_) result_ = consumeToken(builder_, CHAR_LITERAL);
    if (!result_) result_ = consumeToken(builder_, TRUE);
    if (!result_) result_ = consumeToken(builder_, FALSE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // expr COLON expr
  public static boolean map_entry(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MAP_ENTRY, "<map entry>");
    result_ = expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // map_type LBRACE (map_entry (COMMA map_entry)* COMMA?)? RBRACE
  public static boolean map_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_literal")) return false;
    if (!nextTokenIs(builder_, MAP)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = map_type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && map_literal_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, MAP_LITERAL, result_);
    return result_;
  }

  // (map_entry (COMMA map_entry)* COMMA?)?
  private static boolean map_literal_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_literal_2")) return false;
    map_literal_2_0(builder_, level_ + 1);
    return true;
  }

  // map_entry (COMMA map_entry)* COMMA?
  private static boolean map_literal_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_literal_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = map_entry(builder_, level_ + 1);
    result_ = result_ && map_literal_2_0_1(builder_, level_ + 1);
    result_ = result_ && map_literal_2_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA map_entry)*
  private static boolean map_literal_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_literal_2_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!map_literal_2_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "map_literal_2_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA map_entry
  private static boolean map_literal_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_literal_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && map_entry(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA?
  private static boolean map_literal_2_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_literal_2_0_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // MAP LBRACKET type RBRACKET prefix_type
  public static boolean map_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_type")) return false;
    if (!nextTokenIs(builder_, MAP)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, MAP, LBRACKET);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACKET);
    result_ = result_ && prefix_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, MAP_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // STATIC? func_decl
  public static boolean method_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "method_decl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, METHOD_DECL, "<method decl>");
    result_ = method_decl_0(builder_, level_ + 1);
    result_ = result_ && func_decl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // STATIC?
  private static boolean method_decl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "method_decl_0")) return false;
    consumeToken(builder_, STATIC);
    return true;
  }

  /* ********************************************************** */
  // unary_expr ((STAR | SLASH | PERCENT | AT | SHL | SHR | AMP) unary_expr)*
  public static boolean mul_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MUL_EXPR, "<mul expr>");
    result_ = unary_expr(builder_, level_ + 1);
    result_ = result_ && mul_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ((STAR | SLASH | PERCENT | AT | SHL | SHR | AMP) unary_expr)*
  private static boolean mul_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!mul_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "mul_expr_1", pos_)) break;
    }
    return true;
  }

  // (STAR | SLASH | PERCENT | AT | SHL | SHR | AMP) unary_expr
  private static boolean mul_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = mul_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && unary_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR | SLASH | PERCENT | AT | SHL | SHR | AMP
  private static boolean mul_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr_1_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, STAR);
    if (!result_) result_ = consumeToken(builder_, SLASH);
    if (!result_) result_ = consumeToken(builder_, PERCENT);
    if (!result_) result_ = consumeToken(builder_, AT);
    if (!result_) result_ = consumeToken(builder_, SHL);
    if (!result_) result_ = consumeToken(builder_, SHR);
    if (!result_) result_ = consumeToken(builder_, AMP);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER | NAMESPACE | IMPORT | AS | STRUCT | INTERFACE | ENUM | FUNC | STATIC | ASYNC | AWAIT | VAR | CONST | IF | ELSE | FOR | SWITCH | CASE | DEFAULT | RETURN | BREAK | CONTINUE | DEFER | TRY | CATCH | NEW | NIL | TRUE | FALSE | MAP | THIS
  public static boolean name_segment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "name_segment")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NAME_SEGMENT, "<name segment>");
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, NAMESPACE);
    if (!result_) result_ = consumeToken(builder_, IMPORT);
    if (!result_) result_ = consumeToken(builder_, AS);
    if (!result_) result_ = consumeToken(builder_, STRUCT);
    if (!result_) result_ = consumeToken(builder_, INTERFACE);
    if (!result_) result_ = consumeToken(builder_, ENUM);
    if (!result_) result_ = consumeToken(builder_, FUNC);
    if (!result_) result_ = consumeToken(builder_, STATIC);
    if (!result_) result_ = consumeToken(builder_, ASYNC);
    if (!result_) result_ = consumeToken(builder_, AWAIT);
    if (!result_) result_ = consumeToken(builder_, VAR);
    if (!result_) result_ = consumeToken(builder_, CONST);
    if (!result_) result_ = consumeToken(builder_, IF);
    if (!result_) result_ = consumeToken(builder_, ELSE);
    if (!result_) result_ = consumeToken(builder_, FOR);
    if (!result_) result_ = consumeToken(builder_, SWITCH);
    if (!result_) result_ = consumeToken(builder_, CASE);
    if (!result_) result_ = consumeToken(builder_, DEFAULT);
    if (!result_) result_ = consumeToken(builder_, RETURN);
    if (!result_) result_ = consumeToken(builder_, BREAK);
    if (!result_) result_ = consumeToken(builder_, CONTINUE);
    if (!result_) result_ = consumeToken(builder_, DEFER);
    if (!result_) result_ = consumeToken(builder_, TRY);
    if (!result_) result_ = consumeToken(builder_, CATCH);
    if (!result_) result_ = consumeToken(builder_, NEW);
    if (!result_) result_ = consumeToken(builder_, NIL);
    if (!result_) result_ = consumeToken(builder_, TRUE);
    if (!result_) result_ = consumeToken(builder_, FALSE);
    if (!result_) result_ = consumeToken(builder_, MAP);
    if (!result_) result_ = consumeToken(builder_, THIS);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // qualified_name generic_args?
  public static boolean named_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "named_type")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NAMED_TYPE, "<named type>");
    result_ = qualified_name(builder_, level_ + 1);
    result_ = result_ && named_type_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // generic_args?
  private static boolean named_type_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "named_type_1")) return false;
    generic_args(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // NAMESPACE qualified_name eos
  public static boolean namespace_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "namespace_decl")) return false;
    if (!nextTokenIs(builder_, NAMESPACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NAMESPACE);
    result_ = result_ && qualified_name(builder_, level_ + 1);
    result_ = result_ && eos(builder_, level_ + 1);
    exit_section_(builder_, marker_, NAMESPACE_DECL, result_);
    return result_;
  }

  /* ********************************************************** */
  // and_expr (OROR and_expr)*
  public static boolean or_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "or_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, OR_EXPR, "<or expr>");
    result_ = and_expr(builder_, level_ + 1);
    result_ = result_ && or_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (OROR and_expr)*
  private static boolean or_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "or_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!or_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "or_expr_1", pos_)) break;
    }
    return true;
  }

  // OROR and_expr
  private static boolean or_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "or_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OROR);
    result_ = result_ && and_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // attribute* IDENTIFIER (COMMA IDENTIFIER)* type
  public static boolean param_group(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_group")) return false;
    if (!nextTokenIs(builder_, "<param group>", AT, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARAM_GROUP, "<param group>");
    result_ = param_group_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && param_group_2(builder_, level_ + 1);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // attribute*
  private static boolean param_group_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_group_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "param_group_0", pos_)) break;
    }
    return true;
  }

  // (COMMA IDENTIFIER)*
  private static boolean param_group_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_group_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!param_group_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "param_group_2", pos_)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean param_group_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_group_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // param_group (COMMA param_group)* COMMA?
  public static boolean param_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list")) return false;
    if (!nextTokenIs(builder_, "<param list>", AT, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARAM_LIST, "<param list>");
    result_ = param_group(builder_, level_ + 1);
    result_ = result_ && param_list_1(builder_, level_ + 1);
    result_ = result_ && param_list_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COMMA param_group)*
  private static boolean param_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!param_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "param_list_1", pos_)) break;
    }
    return true;
  }

  // COMMA param_group
  private static boolean param_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && param_group(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA?
  private static boolean param_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // range_pattern | type_bind_pattern | expr
  public static boolean pattern(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pattern")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PATTERN, "<pattern>");
    result_ = range_pattern(builder_, level_ + 1);
    if (!result_) result_ = type_bind_pattern(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER? STAR prefix_type
  public static boolean pointer_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pointer_type")) return false;
    if (!nextTokenIs(builder_, "<pointer type>", IDENTIFIER, STAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POINTER_TYPE, "<pointer type>");
    result_ = pointer_type_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, STAR);
    result_ = result_ && prefix_type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENTIFIER?
  private static boolean pointer_type_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pointer_type_0")) return false;
    consumeToken(builder_, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // DOT name_segment
  //           | LPAREN arg_list? RPAREN
  //           | LBRACKET (range_expr | expr) RBRACKET
  //           | QUESTION (LPAREN expr RPAREN)?
  //           // explicit type arguments `Max<int>(1, 2)`, `Option<int>.Some(1)`, `F<int>`;
  //           // the compiler accepts them only before ( { . ) ] } , or the end of a statement
  //           | generic_args
  public static boolean postfix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POSTFIX, "<postfix>");
    result_ = postfix_0(builder_, level_ + 1);
    if (!result_) result_ = postfix_1(builder_, level_ + 1);
    if (!result_) result_ = postfix_2(builder_, level_ + 1);
    if (!result_) result_ = postfix_3(builder_, level_ + 1);
    if (!result_) result_ = generic_args(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // DOT name_segment
  private static boolean postfix_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && name_segment(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LPAREN arg_list? RPAREN
  private static boolean postfix_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && postfix_1_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arg_list?
  private static boolean postfix_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_1_1")) return false;
    arg_list(builder_, level_ + 1);
    return true;
  }

  // LBRACKET (range_expr | expr) RBRACKET
  private static boolean postfix_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACKET);
    result_ = result_ && postfix_2_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // range_expr | expr
  private static boolean postfix_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_2_1")) return false;
    boolean result_;
    result_ = range_expr(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1);
    return result_;
  }

  // QUESTION (LPAREN expr RPAREN)?
  private static boolean postfix_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, QUESTION);
    result_ = result_ && postfix_3_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (LPAREN expr RPAREN)?
  private static boolean postfix_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_3_1")) return false;
    postfix_3_1_0(builder_, level_ + 1);
    return true;
  }

  // LPAREN expr RPAREN
  private static boolean postfix_3_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_3_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // primary_expr postfix*
  public static boolean postfix_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POSTFIX_EXPR, "<postfix expr>");
    result_ = primary_expr(builder_, level_ + 1);
    result_ = result_ && postfix_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // postfix*
  private static boolean postfix_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!postfix(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "postfix_expr_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // pointer_type | slice_type | array_type | map_type | func_type | tuple_type | named_type
  public static boolean prefix_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefix_type")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PREFIX_TYPE, "<prefix type>");
    result_ = pointer_type(builder_, level_ + 1);
    if (!result_) result_ = slice_type(builder_, level_ + 1);
    if (!result_) result_ = array_type(builder_, level_ + 1);
    if (!result_) result_ = map_type(builder_, level_ + 1);
    if (!result_) result_ = func_type(builder_, level_ + 1);
    if (!result_) result_ = tuple_type(builder_, level_ + 1);
    if (!result_) result_ = named_type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // lambda_expr | literal | THIS | NIL | UNDERSCORE
  //                | struct_literal | slice_literal | map_literal
  //                | NEW primary_expr
  //                | LPAREN expr (COMMA expr)* RPAREN
  //                | IDENTIFIER
  public static boolean primary_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PRIMARY_EXPR, "<primary expr>");
    result_ = lambda_expr(builder_, level_ + 1);
    if (!result_) result_ = literal(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, THIS);
    if (!result_) result_ = consumeToken(builder_, NIL);
    if (!result_) result_ = consumeToken(builder_, UNDERSCORE);
    if (!result_) result_ = struct_literal(builder_, level_ + 1);
    if (!result_) result_ = slice_literal(builder_, level_ + 1);
    if (!result_) result_ = map_literal(builder_, level_ + 1);
    if (!result_) result_ = primary_expr_8(builder_, level_ + 1);
    if (!result_) result_ = primary_expr_9(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // NEW primary_expr
  private static boolean primary_expr_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_expr_8")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, NEW);
    result_ = result_ && primary_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LPAREN expr (COMMA expr)* RPAREN
  private static boolean primary_expr_9(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_expr_9")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && primary_expr_9_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA expr)*
  private static boolean primary_expr_9_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_expr_9_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!primary_expr_9_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "primary_expr_9_2", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean primary_expr_9_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_expr_9_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // name_segment (DOT name_segment)*
  public static boolean qualified_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qualified_name")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, QUALIFIED_NAME, "<qualified name>");
    result_ = name_segment(builder_, level_ + 1);
    result_ = result_ && qualified_name_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (DOT name_segment)*
  private static boolean qualified_name_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qualified_name_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!qualified_name_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "qualified_name_1", pos_)) break;
    }
    return true;
  }

  // DOT name_segment
  private static boolean qualified_name_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qualified_name_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && name_segment(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // expr? DOTDOT expr?
  public static boolean range_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RANGE_EXPR, "<range expr>");
    result_ = range_expr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DOTDOT);
    result_ = result_ && range_expr_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // expr?
  private static boolean range_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_0")) return false;
    expr(builder_, level_ + 1);
    return true;
  }

  // expr?
  private static boolean range_expr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_2")) return false;
    expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // range_expr
  public static boolean range_pattern(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_pattern")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RANGE_PATTERN, "<range pattern>");
    result_ = range_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // RETURN (expr (COMMA expr)*)?
  public static boolean return_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt")) return false;
    if (!nextTokenIs(builder_, RETURN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RETURN);
    result_ = result_ && return_stmt_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, RETURN_STMT, result_);
    return result_;
  }

  // (expr (COMMA expr)*)?
  private static boolean return_stmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt_1")) return false;
    return_stmt_1_0(builder_, level_ + 1);
    return true;
  }

  // expr (COMMA expr)*
  private static boolean return_stmt_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expr(builder_, level_ + 1);
    result_ = result_ && return_stmt_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA expr)*
  private static boolean return_stmt_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!return_stmt_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "return_stmt_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean return_stmt_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LBRACKET (expr (COMMA expr)* COMMA?)? RBRACKET
  public static boolean slice_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_literal")) return false;
    if (!nextTokenIs(builder_, LBRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LBRACKET);
    result_ = result_ && slice_literal_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACKET);
    exit_section_(builder_, marker_, SLICE_LITERAL, result_);
    return result_;
  }

  // (expr (COMMA expr)* COMMA?)?
  private static boolean slice_literal_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_literal_1")) return false;
    slice_literal_1_0(builder_, level_ + 1);
    return true;
  }

  // expr (COMMA expr)* COMMA?
  private static boolean slice_literal_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_literal_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expr(builder_, level_ + 1);
    result_ = result_ && slice_literal_1_0_1(builder_, level_ + 1);
    result_ = result_ && slice_literal_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA expr)*
  private static boolean slice_literal_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_literal_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!slice_literal_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "slice_literal_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean slice_literal_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_literal_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA?
  private static boolean slice_literal_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_literal_1_0_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // LBRACKET RBRACKET prefix_type
  public static boolean slice_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "slice_type")) return false;
    if (!nextTokenIs(builder_, LBRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, LBRACKET, RBRACKET);
    result_ = result_ && prefix_type(builder_, level_ + 1);
    exit_section_(builder_, marker_, SLICE_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // (var_decl | const_decl | if_stmt | for_stmt | switch_stmt | try_stmt | return_stmt
  //              | break_stmt | continue_stmt | defer_stmt | block | assign_stmt | expr_stmt) eos?
  public static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STATEMENT, "<statement>");
    result_ = statement_0(builder_, level_ + 1);
    result_ = result_ && statement_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // var_decl | const_decl | if_stmt | for_stmt | switch_stmt | try_stmt | return_stmt
  //              | break_stmt | continue_stmt | defer_stmt | block | assign_stmt | expr_stmt
  private static boolean statement_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_0")) return false;
    boolean result_;
    result_ = var_decl(builder_, level_ + 1);
    if (!result_) result_ = const_decl(builder_, level_ + 1);
    if (!result_) result_ = if_stmt(builder_, level_ + 1);
    if (!result_) result_ = for_stmt(builder_, level_ + 1);
    if (!result_) result_ = switch_stmt(builder_, level_ + 1);
    if (!result_) result_ = try_stmt(builder_, level_ + 1);
    if (!result_) result_ = return_stmt(builder_, level_ + 1);
    if (!result_) result_ = break_stmt(builder_, level_ + 1);
    if (!result_) result_ = continue_stmt(builder_, level_ + 1);
    if (!result_) result_ = defer_stmt(builder_, level_ + 1);
    if (!result_) result_ = block(builder_, level_ + 1);
    if (!result_) result_ = assign_stmt(builder_, level_ + 1);
    if (!result_) result_ = expr_stmt(builder_, level_ + 1);
    return result_;
  }

  // eos?
  private static boolean statement_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_1")) return false;
    eos(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // STRUCT (IDENTIFIER | DEFAULT) generic_params? implements_clause? LBRACE struct_member* RBRACE
  public static boolean struct_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_decl")) return false;
    if (!nextTokenIs(builder_, STRUCT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRUCT);
    result_ = result_ && struct_decl_1(builder_, level_ + 1);
    result_ = result_ && struct_decl_2(builder_, level_ + 1);
    result_ = result_ && struct_decl_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && struct_decl_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, STRUCT_DECL, result_);
    return result_;
  }

  // IDENTIFIER | DEFAULT
  private static boolean struct_decl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_decl_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, DEFAULT);
    return result_;
  }

  // generic_params?
  private static boolean struct_decl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_decl_2")) return false;
    generic_params(builder_, level_ + 1);
    return true;
  }

  // implements_clause?
  private static boolean struct_decl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_decl_3")) return false;
    implements_clause(builder_, level_ + 1);
    return true;
  }

  // struct_member*
  private static boolean struct_decl_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_decl_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!struct_member(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "struct_decl_5", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // qualified_name generic_args? LBRACE (field_init (COMMA field_init)* COMMA?)? RBRACE
  public static boolean struct_literal(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_LITERAL, "<struct literal>");
    result_ = qualified_name(builder_, level_ + 1);
    result_ = result_ && struct_literal_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && struct_literal_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // generic_args?
  private static boolean struct_literal_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal_1")) return false;
    generic_args(builder_, level_ + 1);
    return true;
  }

  // (field_init (COMMA field_init)* COMMA?)?
  private static boolean struct_literal_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal_3")) return false;
    struct_literal_3_0(builder_, level_ + 1);
    return true;
  }

  // field_init (COMMA field_init)* COMMA?
  private static boolean struct_literal_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = field_init(builder_, level_ + 1);
    result_ = result_ && struct_literal_3_0_1(builder_, level_ + 1);
    result_ = result_ && struct_literal_3_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA field_init)*
  private static boolean struct_literal_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal_3_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!struct_literal_3_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "struct_literal_3_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA field_init
  private static boolean struct_literal_3_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal_3_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && field_init(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA?
  private static boolean struct_literal_3_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_literal_3_0_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // attribute* (method_decl | field_decl | embed_decl) eos?
  public static boolean struct_member(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_member")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_MEMBER, "<struct member>");
    result_ = struct_member_0(builder_, level_ + 1);
    result_ = result_ && struct_member_1(builder_, level_ + 1);
    result_ = result_ && struct_member_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // attribute*
  private static boolean struct_member_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_member_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "struct_member_0", pos_)) break;
    }
    return true;
  }

  // method_decl | field_decl | embed_decl
  private static boolean struct_member_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_member_1")) return false;
    boolean result_;
    result_ = method_decl(builder_, level_ + 1);
    if (!result_) result_ = field_decl(builder_, level_ + 1);
    if (!result_) result_ = embed_decl(builder_, level_ + 1);
    return result_;
  }

  // eos?
  private static boolean struct_member_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "struct_member_2")) return false;
    eos(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // CASE pattern (COMMA pattern)* COLON statement*
  public static boolean switch_case(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_case")) return false;
    if (!nextTokenIs(builder_, CASE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CASE);
    result_ = result_ && pattern(builder_, level_ + 1);
    result_ = result_ && switch_case_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && switch_case_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, SWITCH_CASE, result_);
    return result_;
  }

  // (COMMA pattern)*
  private static boolean switch_case_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_case_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!switch_case_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "switch_case_2", pos_)) break;
    }
    return true;
  }

  // COMMA pattern
  private static boolean switch_case_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_case_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && pattern(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // statement*
  private static boolean switch_case_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_case_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "switch_case_4", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SWITCH expr? LBRACE switch_case* default_case? RBRACE
  public static boolean switch_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_stmt")) return false;
    if (!nextTokenIs(builder_, SWITCH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SWITCH);
    result_ = result_ && switch_stmt_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LBRACE);
    result_ = result_ && switch_stmt_3(builder_, level_ + 1);
    result_ = result_ && switch_stmt_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RBRACE);
    exit_section_(builder_, marker_, SWITCH_STMT, result_);
    return result_;
  }

  // expr?
  private static boolean switch_stmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_stmt_1")) return false;
    expr(builder_, level_ + 1);
    return true;
  }

  // switch_case*
  private static boolean switch_stmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_stmt_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!switch_case(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "switch_stmt_3", pos_)) break;
    }
    return true;
  }

  // default_case?
  private static boolean switch_stmt_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_stmt_4")) return false;
    default_case(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // attribute* ((struct_decl | interface_decl | enum_decl | func_decl) eos? | extern_func_decl eos | var_decl eos | const_decl eos | type_alias_decl eos)
  public static boolean top_level_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TOP_LEVEL_DECL, "<top level decl>");
    result_ = top_level_decl_0(builder_, level_ + 1);
    result_ = result_ && top_level_decl_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // attribute*
  private static boolean top_level_decl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!attribute(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "top_level_decl_0", pos_)) break;
    }
    return true;
  }

  // (struct_decl | interface_decl | enum_decl | func_decl) eos? | extern_func_decl eos | var_decl eos | const_decl eos | type_alias_decl eos
  private static boolean top_level_decl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = top_level_decl_1_0(builder_, level_ + 1);
    if (!result_) result_ = top_level_decl_1_1(builder_, level_ + 1);
    if (!result_) result_ = top_level_decl_1_2(builder_, level_ + 1);
    if (!result_) result_ = top_level_decl_1_3(builder_, level_ + 1);
    if (!result_) result_ = top_level_decl_1_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (struct_decl | interface_decl | enum_decl | func_decl) eos?
  private static boolean top_level_decl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = top_level_decl_1_0_0(builder_, level_ + 1);
    result_ = result_ && top_level_decl_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // struct_decl | interface_decl | enum_decl | func_decl
  private static boolean top_level_decl_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_0_0")) return false;
    boolean result_;
    result_ = struct_decl(builder_, level_ + 1);
    if (!result_) result_ = interface_decl(builder_, level_ + 1);
    if (!result_) result_ = enum_decl(builder_, level_ + 1);
    if (!result_) result_ = func_decl(builder_, level_ + 1);
    return result_;
  }

  // eos?
  private static boolean top_level_decl_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_0_1")) return false;
    eos(builder_, level_ + 1);
    return true;
  }

  // extern_func_decl eos
  private static boolean top_level_decl_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = extern_func_decl(builder_, level_ + 1);
    result_ = result_ && eos(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // var_decl eos
  private static boolean top_level_decl_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = var_decl(builder_, level_ + 1);
    result_ = result_ && eos(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // const_decl eos
  private static boolean top_level_decl_1_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = const_decl(builder_, level_ + 1);
    result_ = result_ && eos(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // type_alias_decl eos
  private static boolean top_level_decl_1_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "top_level_decl_1_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = type_alias_decl(builder_, level_ + 1);
    result_ = result_ && eos(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // TRY expr (CATCH var_name block | ELSE else_value)
  public static boolean try_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_expr")) return false;
    if (!nextTokenIs(builder_, TRY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TRY);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && try_expr_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRY_EXPR, result_);
    return result_;
  }

  // CATCH var_name block | ELSE else_value
  private static boolean try_expr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_expr_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = try_expr_2_0(builder_, level_ + 1);
    if (!result_) result_ = try_expr_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CATCH var_name block
  private static boolean try_expr_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_expr_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CATCH);
    result_ = result_ && var_name(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ELSE else_value
  private static boolean try_expr_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_expr_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE);
    result_ = result_ && else_value(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // TRY block CATCH var_name block
  public static boolean try_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt")) return false;
    if (!nextTokenIs(builder_, TRY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TRY);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CATCH);
    result_ = result_ && var_name(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRY_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // LPAREN type (COMMA type)+ RPAREN
  public static boolean tuple_type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_type")) return false;
    if (!nextTokenIs(builder_, LPAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && type(builder_, level_ + 1);
    result_ = result_ && tuple_type_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, TUPLE_TYPE, result_);
    return result_;
  }

  // (COMMA type)+
  private static boolean tuple_type_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_type_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = tuple_type_2_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!tuple_type_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "tuple_type_2", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA type
  private static boolean tuple_type_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tuple_type_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // prefix_type BANG?
  public static boolean type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE, "<type>");
    result_ = prefix_type(builder_, level_ + 1);
    result_ = result_ && type_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // BANG?
  private static boolean type_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_1")) return false;
    consumeToken(builder_, BANG);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER IDENTIFIER ASSIGN type
  public static boolean type_alias_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_alias_decl")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, IDENTIFIER, ASSIGN);
    result_ = result_ && type(builder_, level_ + 1);
    exit_section_(builder_, marker_, TYPE_ALIAS_DECL, result_);
    return result_;
  }

  /* ********************************************************** */
  // type IDENTIFIER &(COLON | COMMA)
  public static boolean type_bind_pattern(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_bind_pattern")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_BIND_PATTERN, "<type bind pattern>");
    result_ = type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && type_bind_pattern_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // &(COLON | COMMA)
  private static boolean type_bind_pattern_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_bind_pattern_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = type_bind_pattern_2_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COLON | COMMA
  private static boolean type_bind_pattern_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "type_bind_pattern_2_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COLON);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    return result_;
  }

  /* ********************************************************** */
  // (MINUS | BANG | AMP | STAR | AWAIT) unary_expr | postfix_expr
  public static boolean unary_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, UNARY_EXPR, "<unary expr>");
    result_ = unary_expr_0(builder_, level_ + 1);
    if (!result_) result_ = postfix_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (MINUS | BANG | AMP | STAR | AWAIT) unary_expr
  private static boolean unary_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = unary_expr_0_0(builder_, level_ + 1);
    result_ = result_ && unary_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MINUS | BANG | AMP | STAR | AWAIT
  private static boolean unary_expr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_expr_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, MINUS);
    if (!result_) result_ = consumeToken(builder_, BANG);
    if (!result_) result_ = consumeToken(builder_, AMP);
    if (!result_) result_ = consumeToken(builder_, STAR);
    if (!result_) result_ = consumeToken(builder_, AWAIT);
    return result_;
  }

  /* ********************************************************** */
  // VAR (var_name (COMMA var_name)* | var_tuple) type? (ASSIGN expr)?
  public static boolean var_decl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VAR);
    result_ = result_ && var_decl_1(builder_, level_ + 1);
    result_ = result_ && var_decl_2(builder_, level_ + 1);
    result_ = result_ && var_decl_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, VAR_DECL, result_);
    return result_;
  }

  // var_name (COMMA var_name)* | var_tuple
  private static boolean var_decl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = var_decl_1_0(builder_, level_ + 1);
    if (!result_) result_ = var_tuple(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // var_name (COMMA var_name)*
  private static boolean var_decl_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = var_name(builder_, level_ + 1);
    result_ = result_ && var_decl_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA var_name)*
  private static boolean var_decl_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!var_decl_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "var_decl_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA var_name
  private static boolean var_decl_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && var_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // type?
  private static boolean var_decl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_2")) return false;
    type(builder_, level_ + 1);
    return true;
  }

  // (ASSIGN expr)?
  private static boolean var_decl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_3")) return false;
    var_decl_3_0(builder_, level_ + 1);
    return true;
  }

  // ASSIGN expr
  private static boolean var_decl_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_decl_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ASSIGN);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER | UNDERSCORE
  public static boolean var_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_name")) return false;
    if (!nextTokenIs(builder_, "<var name>", IDENTIFIER, UNDERSCORE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VAR_NAME, "<var name>");
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, UNDERSCORE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LPAREN var_name (COMMA var_name)* RPAREN
  public static boolean var_tuple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_tuple")) return false;
    if (!nextTokenIs(builder_, LPAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LPAREN);
    result_ = result_ && var_name(builder_, level_ + 1);
    result_ = result_ && var_tuple_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RPAREN);
    exit_section_(builder_, marker_, VAR_TUPLE, result_);
    return result_;
  }

  // (COMMA var_name)*
  private static boolean var_tuple_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_tuple_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!var_tuple_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "var_tuple_2", pos_)) break;
    }
    return true;
  }

  // COMMA var_name
  private static boolean var_tuple_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_tuple_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && var_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

}
