package com.kh.library.run;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.library.controller.BookController;
import com.kh.library.model.vo.Book;
import com.kh.library.model.vo.Magazine;

public class LibraryRun {

	public static void main(String[] args) {// 소장책 DB
		ArrayList<Book> bList = new ArrayList<>();

		bList.add(new Book("100", "잃어버린 승호를 찾아서", "강인호 박사", "동현 출판사", 8500, "『수민스』, 『르 몽드』 선정 20세기 최고의 책"));
		bList.add(new Book("101", "수진이의 자서전", "연준이가 옮김", "빛나는 소영출판사", 8500, "영화배우 박수현이 추천한 젊음에 바치는 영혼의 자서전"));
		bList.add(new Magazine("102", "박연준 과학동아", "편집부 박준호 선생", "진우 사이언스", 8500, "희연 유튜브에 소개된 과학만화 잡지", 2022, 3));
		bList.add(new Book("103", "여진이의 코딩스쿨", "코딩천재 이윤화", "종규네 동네", 9900, "제 13회 지원이가 주는 문학상 수상작"));
		bList.add(new Magazine("104", "형근이의 여행일기", "혜민쓰", "시연21", 3800, "영화감독 임준석이 집필한 영화 전문 잡지", 2021, 4));
		bList.add(new Magazine("105", "철현의 데일리룩", "인플루언서 전혜정", "민교네 책방", 12000, "핫걸 정지용의 샤라웃을 받은 잡지", 2022, 11));
		bList.add(new Book("106", "조진원의 미라클모닝", "민수찡", "승협쓰 출판사", 6300, "영재도 2번 성공한 미라클모닝"));
		bList.add(new Book("107", "희승이의 백만가지 요리레시피", "제주도에서 온 수빈이", "우리반 출판사", 6300, "제주에서 밥해먹고 사는 일상"));

		printBooks(bList);

	}

	public static void printBooks(ArrayList<Book> bs) {

		BookController bc = new BookController();

		for (Book b : bs) {
			System.out.println(b);
			bc.addBook(b);
		}

		boolean flag = true;

		while (flag = true) {
			System.out.println("===============================");
			System.out.println("KH 우리반전용 도서관 관리 페이지입니다.");
			System.out.println("1. 조회하기");
			System.out.println("2. 추가하기");
			System.out.println("3. 책 찾기");
			System.out.println("4. 전체책 가격 합계 및 평균 조회");
			System.out.println("5. 책 정보를 파일로 저장");
			System.out.println("6. 프로그램 종료");
			System.out.println("===============================");

			System.out.print("메뉴번호를 입력해주세요 : ");
			Scanner sc = new Scanner(System.in);
			int mainNum = sc.nextInt();

			switch (mainNum) {
			case 1: // 1.조회하기
				System.out.println("===============================");
				System.out.println("어떤 방법으로 책을 조회하시겠습니까?");
				System.out.println("1. 도서관 전체 소장책 조회하기");
				System.out.println("2. 일반도서만 조회하기(잡지 제외)");
				System.out.println("3. 잡지만 조회하기(일반도서 제외)");
				System.out.println("4. 이전으로");
				System.out.println("===============================");
				System.out.print("메뉴번호를 입력해주세요 : ");
				int lookupNum = sc.nextInt();

				if (lookupNum == 1) {
					System.out.println(bc.getAllBook());
				} else if (lookupNum == 2) {
					System.out.println(bc.onlySearchBook());
				} else if (lookupNum == 3) {
					System.out.println(bc.onlySearchMagazine());
				} else if (lookupNum == 4) {
					flag = true;
				} else {
					System.out.println("잘못입력하셨습니다. 메인메뉴로 돌아갑니다.");
				}
				break;

			case 2: // 2.추가하기
				System.out.print("bNo을 입력하세요 : ");
				String bNo = sc.next();

				System.out.print("책 제목을 입력하세요 : ");
				String title = sc.next();

				System.out.print("작가를 입력하세요 : ");
				String author = sc.next();

				System.out.print("출판사를 입력하세요 : ");
				String publisher = sc.next();

				System.out.print("가격을 입력하세요 : ");
				int price = sc.nextInt();

				sc.nextLine();

				System.out.print("간단한 설명을 입력하세요 : ");
				String description = sc.next();

				System.out.print("일반도서이면 true, 잡지이면 false를 입력하세요 : ");
				boolean bkOrMaga = sc.nextBoolean();

				if (bkOrMaga == true) {
					bc.addBook(new Book(bNo, title, author, publisher, price, description));
				} else if (bkOrMaga == false) {
					System.out.print("출간연도를 입력하세요 : ");
					int year = sc.nextInt();
					System.out.print("출간월를 입력하세요 : ");
					int month = sc.nextInt();
					bc.addBook(new Magazine(bNo, title, author, publisher, price, description, year, month));
				}
				break;

			case 3: // 3.책찾기
				System.out.println("===============================");
				System.out.println("어떤 방법으로 책을 찾으시겠습니까?");
				System.out.println("1. bNo으로 책 찾기");
				System.out.println("2. 책 제목으로 책 찾기");
				System.out.println("3. 출간연도로 잡지 찾기");
				System.out.println("4. 출판사로 책 찾기");
				System.out.println("5. 특정가격 밑으로 책 찾기");
				System.out.println("6. 이전으로");
				System.out.println("===============================");
				System.out.print("메뉴번호를 입력해주세요 : ");
				int searchNum = sc.nextInt();

				if (searchNum == 1) {
					System.out.print("bNo을 입력하세요 : ");
					String find = sc.next();
					System.out.println(bc.searchBookBybNo(find));
				} else if (searchNum == 2) {
					System.out.print("책 제목을 입력하세요 : ");
					String find = sc.next();
					System.out.println(bc.searchBookByTitle(find));
				} else if (searchNum == 3) {
					System.out.print("출간연도를 입력하세요 : ");
					int find = sc.nextInt();
					System.out.println(bc.magazineOfThisYearInfo(find));
				} else if (searchNum == 4) {
					System.out.print("출판사를 입력하세요 : ");
					String find = sc.next();
					System.out.println(bc.searchBookByPublisher(find));
				} else if (searchNum == 5) {
					System.out.print("가격을 입력하세요 : ");
					int find = sc.nextInt();
					System.out.println(bc.searchBookByPrice(find));
				} else if (searchNum == 6) {
					flag = true;
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
				break;

			case 4: // 4. 전체책 가격 합계 및 평균 조회s
				System.out.println("전체 책 가격 합계 : " + bc.getTotalPrice() + "원");
				System.out.println("전체 책 가격 평균 : " + bc.getAvgPrice() + "원");
				break;
			case 5: 
				bc.fileSave();
			case 6: // 6. 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못입력하셨습니다. 다시입력해주세요.");
				break;
			}
		}
	}
}
