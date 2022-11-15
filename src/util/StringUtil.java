package util;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Util for String
 * 
 * @author liu yuning
 *
 */
public class StringUtil {

    private static final int DEFAULT_STRING_LENGTH = 10;

    private static final String LETTERS = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static Random random = new Random();

    /**
     * Don't let anyone instantiate this class.
     */
    private StringUtil() {

    }

    /**
     * 
     * @param str
     * @return {@code true} if the input {@code String} is null or ""
     */
    public static boolean empty(final String str) {
	return str == null || str.trim().isEmpty();
    }

    /**
     * generate random string
     * 
     * @return a random string
     */
    public static String generateRandomString() {
	return generateRandomString(DEFAULT_STRING_LENGTH);
    }

    /**
     * generate random string using all letters and the given length
     * 
     * @param stringLength
     * @return a random string
     */
    public static String generateRandomString(int stringLength) {
	if (stringLength <= 0) {
	    stringLength = DEFAULT_STRING_LENGTH;
	}

	StringBuilder stringBuilder = new StringBuilder();

	for (int i = 0; i < stringLength; i++) {
	    stringBuilder
		    .append(LETTERS.charAt(random.nextInt(LETTERS.length())));
	}

	return stringBuilder.toString();
    }

    /**
     * repeat a {@code String} called repeatStr for repeatNum times
     * 
     * @param repeatStr
     * @param repeatNum
     * @return {@code String} repeatedString
     */
    public static String repeatableString(String repeatStr, int repeatNum) {
	StringBuilder stringBuilder = new StringBuilder();

	while (repeatNum-- > 0) {
	    stringBuilder.append(repeatStr);
	}

	return stringBuilder.toString();
    }
    public static void main(String[] args) throws Exception {
        State constants[] = State.values();
        System.out.println("Value of constants: ");
        for(State d: constants) {
            System.out.println(d.getCode()+": "+d);
        }
        System.out.println("Select one model: ");
        Scanner sc = new Scanner(System.in);
        int model = sc.nextInt();

        IState iState=Cities.Delhi;
        Method method = iState.getClass().getMethod("values");
        IState[] inter = (IState[]) method.invoke(null);
        for (IState enumMessage : inter) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", enumMessage.getCode());
            map.put("desc", enumMessage.getDesc());
            map.put("state", enumMessage.getState());
            Optional<State> optionalState= enumMessage.getState().stream().filter(p->p.getCode().equals(model)).findAny();
            if(optionalState.isPresent()) {
                System.out.println(map);
                System.out.printf("code:%s",enumMessage.getCode());
            }
        }
    }
}

enum State {
    Telangana(1,"Telangana"),
    Delhi(2,"Delhi"),
    Tamilnadu(3,"Tamilnadu"),
    Karnataka(4,"Karnataka"),
    Andhrapradesh(5,"Andhrapradesh");
    private Integer code;
    private String  desc;
    State(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }
}

enum Cities implements IState {
    Hyderabad(1,"Hyderabad",State.Telangana,State.Delhi),
    Delhi(2,"Delhi",State.Tamilnadu,State.Karnataka,State.Andhrapradesh);
    //实例变量
    private Integer code;
    private String  desc;
    private List<State> state;
    //构造函数初始化实例变量
    Cities(Integer code,String desc,State ... state){
        this.code=code;
        this.desc=desc;
        this.state = Arrays.asList(state);
    }
    @Override
    public Integer getCode(){
        return this.code;
    }
    @Override
    public String getDesc(){
        return this.desc;
    }
    @Override
    public List<State> getState(){
        return this.state;
    }

}
interface IState {
     Integer getCode();
     String getDesc();
     List<State> getState();
}



