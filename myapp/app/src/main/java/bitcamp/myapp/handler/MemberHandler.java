package bitcamp.myapp.handler;

import bitcamp.util.Prompt;
//
public class MemberHandler {

  static final int MAX_SIZE = 100;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    name[length] = Prompt.inputString("이름? ");
    email[length] = Prompt.inputString("이메일? ");
    password[length] = Prompt.inputString("암호? ");

    loop:
    while (true) {
      String menuNo = Prompt.inputString("성별:\n" +
              "  1. 남자\n" +
              "  2. 여자\n" +
              "> ");

      switch (menuNo) {
        case "1":
          gender[length] = MALE;
          break loop;
        case "2":
          gender[length] = FEMALE;
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }

    no[length] = userId++;
    length++;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %s\n",
              no[i], name[i], email[i],
              toGenderString(gender[i]));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("이메일: %s\n", email[i]);
        System.out.printf("성별: %s\n", toGenderString(gender[i]));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }

  public static void updateMember() {
    String memberNo = Prompt.inputString("수정할 회원의 번호? ");
    int index = findMemberIndex(memberNo);

    if (index == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    System.out.println("수정할 정보를 입력하세요. (변경하지 않을 항목은 엔터를 입력하세요)");

    String newName = Prompt.inputString("이름? ");
    if (!newName.equals("")) {
      name[index] = newName;
    }

    String newEmail = Prompt.inputString("이메일? ");
    if (!newEmail.equals("")) {
      email[index] = newEmail;
    }

    String newPassword = Prompt.inputString("암호? ");
    if (!newPassword.equals("")) {
      password[index] = newPassword;
    }

    String newGender = Prompt.inputString("성별:\n" +
            "  1. 남자\n" +
            "  2. 여자\n" +
            "> ");
    if (!newGender.equals("")) {
      gender[index] = newGender.toUpperCase().charAt(0);
    }

    System.out.println("회원 정보를 수정하였습니다.");
  }

  private static int findMemberIndex(String memberNo) {
    int targetNo = Integer.parseInt(memberNo);
    for (int i = 0; i < length; i++) {
      if (no[i] == targetNo) {
        return i;
      }
    }
    return -1;
  }
}

