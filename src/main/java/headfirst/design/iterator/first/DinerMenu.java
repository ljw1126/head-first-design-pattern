package headfirst.design.iterator.first;

import java.util.Iterator;
public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("채식주의자용 BLT",
                "통밀 위에 콩고기 베이커, 상추, 토마토를 얹은 메뉴", true, 2.99);
        addItem("BLT",
                "통밀 위에 베이컨, 상추, 토마토를 얹은 메뉴", false, 2.99);
        addItem("오늘의 스프",
                "감자 샐러드를 곁들인 오늘의 스프", false, 3.29);
        addItem("핫도그",
                "사우어크라우트, 렐리시, 양파, 치즈를 얹은 핫도그",
                false, 3.05);
        addItem("찐 야채와 현미",
                "현미밥 위에 찐 야채", true, 3.99);
        addItem("파스타",
                "마리나라 소스 스파게티와 사워도우 빵 한 조각",
                true, 3.89);
    }

    public void addItem(String name, String description,
                        boolean vegetarian, double price) {
        if(numberOfItems >= MAX_ITEMS) {
            System.out.println("죄송합니다, 메뉴가 꽉 찼습니다. 더 이상 추가할 수 없습니다.");
            return;
        }

        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems[numberOfItems++] = menuItem;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}
