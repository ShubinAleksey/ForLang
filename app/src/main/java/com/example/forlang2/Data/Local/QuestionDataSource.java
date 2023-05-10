package com.example.forlang2.Data.Local;

import com.example.forlang2.Data.DataSource;
import com.example.forlang2.Model.PairModel;
import com.example.forlang2.Model.QuestionModel;
import com.example.forlang2.Model.TextModel;

import java.util.ArrayList;
import java.util.Random;

public class QuestionDataSource implements DataSource.Local {
    private static QuestionDataSource INSTANCE;

    QuestionModel questionModel;
    TextModel textModel;

    ArrayList<String> question = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();

    Random random = new Random();

    public static QuestionDataSource getInstance() {

        if (INSTANCE == null) {

            INSTANCE = new QuestionDataSource();
        }

        return INSTANCE;
    }

    public QuestionModel getRandomQuestionObj() {

        question.add("She eats apple");
        question.add("He eats");
        question.add("You are a woman");
        question.add("You are a boy");
        question.add("What happened");
        question.add("I am a boy");
        question.add("He eats apple");
        question.add("He eats watermelon");
        question.add("She eats watermelon");
        question.add("Whats going on?");
        question.add("I have a cat");
        question.add("I have a dog");
        question.add("I have a bird");

        answer.add("Она ест яблоко");
        answer.add("Он ест");
        answer.add("Ты женщина");
        answer.add("Ты мальчик");
        answer.add("Что случилось");
        answer.add("Я мальчик");
        answer.add("Он ест яблоко");
        answer.add("Он ест арбуз");
        answer.add("Она ест арбуз");
        answer.add("Что происходит");
        answer.add("У меня есть кошка");
        answer.add("У меня есть собака");
        answer.add("У меня есть птица");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairs() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("apple", "яблоко"));
        pairs.add(new PairModel("milk", "молоко"));
        pairs.add(new PairModel("bread", "хлеб"));
        pairs.add(new PairModel("boy", "мальчик"));
        pairs.add(new PairModel("she", "она"));
        pairs.add(new PairModel("cake", "торт"));
        pairs.add(new PairModel("he", "он"));
        pairs.add(new PairModel("girl", "девочка"));
        pairs.add(new PairModel("banana", "банан"));
        pairs.add(new PairModel("you", "ты"));
        pairs.add(new PairModel("drink", "напиток"));
        pairs.add(new PairModel("water", "вода"));

        return pairs;
    }

    @Override
    public ArrayList<PairModel> getPairsAnimals() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("cat", "кошка"));
        pairs.add(new PairModel("dog", "собака"));
        pairs.add(new PairModel("bird", "птица"));
        pairs.add(new PairModel("monkey", "обезьяна"));
        pairs.add(new PairModel("elephant", "слон"));
        pairs.add(new PairModel("giraffe", "жираф"));
        pairs.add(new PairModel("lion", "лев"));
        pairs.add(new PairModel("tiger", "тигр"));
        pairs.add(new PairModel("cow", "корова"));
        pairs.add(new PairModel("fish", "рыба"));
        pairs.add(new PairModel("frog", "лягушка"));
        pairs.add(new PairModel("chicken", "курица"));

        return pairs;
    }

    public QuestionModel getRandomQuestionObjAnimals() {

        question.add("The birds are flying");
        question.add("Lion is the king of animals");
        question.add("Frog is a swamp animal");
        question.add("Tiger is the fastest animal");
        question.add("Cow is the farmer animal");
        question.add("Monkeys love bananas");

        answer.add("Птицы летают");
        answer.add("Лев - король животных");
        answer.add("Лягушка болотистое животное");
        answer.add("Тигр самое быстрое животное");
        answer.add("Корова фермерское животное");
        answer.add("Обезьяны любят бананы");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairsClothes() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("coat", "пальто"));
        pairs.add(new PairModel("shirt", "рубашка"));
        pairs.add(new PairModel("pants", "штаны"));
        pairs.add(new PairModel("shorts", "шорты"));
        pairs.add(new PairModel("skirt", "юбка"));
        pairs.add(new PairModel("robe", "платье"));
        pairs.add(new PairModel("socks", "носки"));
        pairs.add(new PairModel("T-shirt", "футболка"));
        pairs.add(new PairModel("gloves", "перчатки"));
        pairs.add(new PairModel("sweater", "свитер"));
        pairs.add(new PairModel("scarf", "шарф"));
        pairs.add(new PairModel("boots", "ботинки"));

        return pairs;
    }

    public QuestionModel getRandomQuestionObjClothes() {

        question.add("She keep her clothes neat");
        question.add("These clothes don't fit him");
        question.add("Young people loves to wear trendy clothes");
        question.add("T-shirts are more convenient than blouses");
        question.add("He has a good collection of clothes");
        question.add("We go to school in a uniform");

        answer.add("Она держит свою одежду чистой");
        answer.add("Этa одежда на нём плохо сидит");
        answer.add("Молодые люди любят носить модную одежду");
        answer.add("Футболки более удобны, чем блузки");
        answer.add("У него хорошая коллекция одежды");
        answer.add("Мы ходим в школу в форме");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairsFood() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("soup", "суп"));
        pairs.add(new PairModel("scrambled egg", "яичница"));
        pairs.add(new PairModel("tea", "чай"));
        pairs.add(new PairModel("coffee", "кофе"));
        pairs.add(new PairModel("porridge", "каша"));
        pairs.add(new PairModel("milk", "молоко"));
        pairs.add(new PairModel("cream", "сливки"));
        pairs.add(new PairModel("cake", "торт"));
        pairs.add(new PairModel("marmalade", "мармелад"));
        pairs.add(new PairModel("pizza", "пицца"));
        pairs.add(new PairModel("chips", "чипсы"));
        pairs.add(new PairModel("nuts", "орехи"));

        return pairs;
    }

    public QuestionModel getRandomQuestionObjFood() {

        question.add("She doesn't drink white coffee");
        question.add("I love to eat cupcakes with milk");
        question.add("He doesn't like milk and honey");
        question.add("They ordered some pizza at the cafe");
        question.add("We like to drink tea with apple pie");
        question.add("We had a tasty salad for supper today");

        answer.add("Она не пьёт кофе с молоком");
        answer.add("Я люблю есть кексы с молоком");
        answer.add("Он не любит молоко и мёд");
        answer.add("Они заказали пиццу в кафе");
        answer.add("Мы любим пить чай с яблочным пирогом");
        answer.add("У нас был вкусный салат на ужин");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairsGreetings() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("hello", "здравствуйте"));
        pairs.add(new PairModel("goodbye", "досвиданья"));
        pairs.add(new PairModel("hi", "привет"));
        pairs.add(new PairModel("good afternoon", "добрый день"));
        pairs.add(new PairModel("good morning", "доброе утро"));
        pairs.add(new PairModel("good evening", "добрый вечер"));
        pairs.add(new PairModel("nice to meet you", "приятно познакомиться"));
        pairs.add(new PairModel("glad to see you", "рад вас видеть"));
        pairs.add(new PairModel("thanks", "спасибо"));
        pairs.add(new PairModel("please", "пожалуйста"));
        pairs.add(new PairModel("enjoy your meal", "приятного аппетита"));

        return pairs;
    }

    public QuestionModel getRandomQuestionObjGreetings() {

        question.add("Good afternoon");
        question.add("Good morning");
        question.add("Good evening");
        question.add("It's a pleasure to meet you");
        question.add("How it's going?");
        question.add("What's new?");

        answer.add("Добрый день");
        answer.add("Доброе утро");
        answer.add("Добрый вечер");
        answer.add("Рад познакомиться");
        answer.add("Как жизнь?");
        answer.add("Что нового?");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    @Override
    public ArrayList<PairModel> getPairsFraze() {

        ArrayList<PairModel> pairs = new ArrayList<>();

        pairs.add(new PairModel("don't worry", "не волнуйся"));
        pairs.add(new PairModel("excellent", "отлично"));
        pairs.add(new PairModel("help", "помощь"));
        pairs.add(new PairModel("beware", "остерегаться"));
        pairs.add(new PairModel("hurry", "торопиться"));
        pairs.add(new PairModel("nonsense", "вздор"));
        pairs.add(new PairModel("of course", "конечно"));
        pairs.add(new PairModel("okay", "окей"));
        pairs.add(new PairModel("really?", "действительно?"));
        pairs.add(new PairModel("right here", "прямо здесь"));
        pairs.add(new PairModel("see you later", "увидимся позже"));

        return pairs;
    }

    public QuestionModel getRandomQuestionObjFraze() {

        question.add("Long time no see");
        question.add("Till next time");
        question.add("How is it going?");
        question.add("Talk to you later");
        question.add("See you soon");
        question.add("Until we meet again");

        answer.add("Давно не виделись");
        answer.add("До следующего раза");
        answer.add("Как у вас дела?");
        answer.add("Поговорим с тобой позже");
        answer.add("До скорой встречи");
        answer.add("Пока мы снова не встретимся");

        int randomIndex = random.nextInt(question.size());

        questionModel = new QuestionModel(
                question.get(randomIndex),
                answer.get(randomIndex));

        return questionModel;
    }

    public TextModel getRandomTextObj() {

        text.add("She eats apple");
        text.add("He eats");
        text.add("You are a woman");
        text.add("You are a boy");
        text.add("What happened");
        text.add("I am a boy");
        text.add("He eats apple");
        text.add("He eats watermelon");
        text.add("She eats watermelon");
        text.add("Whats going on?");
        text.add("I have a cat");
        text.add("I have a dog");
        text.add("I have a bird");

        int randomIndex = random.nextInt(text.size());

        textModel = new TextModel(text.get(randomIndex));

        return textModel;
    }

    public TextModel getRandomTextObjAnimals() {

        text.add("Monkeys are interesting animals.\n" +
                "They have got hair on their body\n" +
                "and they have got a tail. They have\n" +
                "a face similar to human face.");
        text.add("Cats are small creatures that humans love.\n" +
                "They come in a variety of colours, including white, black, and brown,\n" +
                " and their small twinkling eyes make us adore them even more.\n" +
                "Cats are known for their dislike of human contact,\n" +
                "but they do show affection in other ways.");
        text.add("Frogs are small animals that can jump very well. \n" +
                "Frogs are similar to toads. \n" +
                "However, a frog has smooth skin and long legs. \n" +
                "A toad has rough skin and shorter legs.");
        text.add("The tiger is a big jungle cat known for its hunting prowess. \n" +
                "Being part of the feline family, \n" +
                "they are related to other big cats such as lions and leopards. \n" +
                "Their scientific name is Panthera Tigris. \n" +
                "They are carnivorous in nature and are mostly found in dense jungles \n" +
                "around the world in places such as South East Asia, China, Russia and Africa.");
        text.add("A cow is a domestic animal. \n" +
                "Cows are one of the most innocent animals who are very harmless. \n" +
                "People keep cows at their homes for various benefits. \n" +
                "Cows are four-footed and have a large body. \n" +
                "It has two horns, two eyes plus two ears and one nose and a mouth. \n" +
                "Cows are herbivorous animals. They have a lot of uses to mankind. \n" +
                "In fact, farmers and people keep cows at their homes for the same purposes.");
        text.add("The dog is a pet animal. \n" +
                "A dog has sharp teeth so that it can eat flesh very easily, \n" +
                "it has four legs, two ears, two eyes, a tail, a mouth, and a nose. \n" +
                "It is a very clever animal and is very useful in catching thieves. \n" +
                "It runs very fast, barks loudly and attacks the strangers. \n" +
                "A dog saves the life of the master from danger.");

        int randomIndex = random.nextInt(text.size());

        textModel = new TextModel(text.get(randomIndex));

        return textModel;
    }

    public TextModel getRandomTextObjClothes() {

        text.add("Jeans are a type of pants typically made from sturdy denim fabric. \n" +
                "They are a versatile wardrobe staple that can be dressed up or down \n" +
                "depending on the occasion. Originally designed as workwear for miners \n" +
                "and cowboys, jeans have become a fashion icon and are now worn by people \n" +
                "of all ages and backgrounds.");
        text.add("Socks do much more than prevent your shoes from \n" +
                "rubbing and blisters from forming on your feet. \n" +
                "They provide an ideal environment by aiding in temperature and moisture control.");
        text.add("Fashion is a non-verbal way of communication that conveys \n" +
                "a lot about the person’s personality, background and style. \n" +
                "Earlier it was exclusively the world of the affluent, celebrities and royalty. \n" +
                "However, fashion is now within the reach of the common man, especially the youth. \n" +
                "Besides, dress fashion is an evolution of ideas, which begins as a fad, \n" +
                "but in course of time gets accepted in society as a style, \n" +
                "which could be in attire, behaviour or lifestyle.");
        text.add("T-shirts are the most popular and comfortable \n" +
                "form of clothing known to people of all age groups \n" +
                "be it young people, rock stars and children. \n" +
                "T-shirts are the new form of advertising because you can \n" +
                "easily design them to carry your message across to people \n" +
                "with the help of the person who wears it. \n" +
                "They are mobile and hence the cost of maintenance \n" +
                "will be relatively low.");
        text.add("A jacket is a garment for the upper body, usually extending below the hips. \n" +
                "A jacket typically has sleeves, and fastens in the front or slightly on the side. \n" +
                "A jacket is generally lighter, tighter-fitting, and less insulating than a coat, which is outerwear.");
        text.add("Uniforms are practical since students do not need to \n" +
                "fret about what to wear each new day. \n" +
                "Besides, school uniform makes each student a part of the school. \n" +
                "They are unified under their school name. \n" +
                "Other practicalities of school uniforms are things like \n" +
                "it is easy to spot a child in their uniform.");

        int randomIndex = random.nextInt(text.size());

        textModel = new TextModel(text.get(randomIndex));

        return textModel;
    }

    public TextModel getRandomTextObjFraze() {

        text.add("Hi! Long time no see. How it's going, friend?");
        text.add("Till next time");
        text.add("How is it going?");
        text.add("Talk to you later");
        text.add("See you soon");
        text.add("Until we meet again");

        int randomIndex = random.nextInt(text.size());

        textModel = new TextModel(text.get(randomIndex));

        return textModel;
    }

    public TextModel getRandomTextObjGreetings() {

        text.add("Good afternoon! May your afternoon be light, blessed, enlightened, productive and happy.");
        text.add("May the morning colors paint your life. Good morning!");
        text.add("Here’s wishing you a lovely evening full of love and laughter. Good evening to you, my beautiful friend.");
        text.add("Thanks for recommendations! It's was a pleasure to meet you!");
        text.add("Hi! Long time no see. How it's going, friend?");
        text.add("You're late from school. What's new happened today?");

        int randomIndex = random.nextInt(text.size());

        textModel = new TextModel(text.get(randomIndex));

        return textModel;
    }

    public TextModel getRandomTextObjFood() {

        text.add("Coffee is a drink made from roasted ground beans or seeds. \n" +
                "People love drinking coffee because it provides caffeine. \n" +
                "People also love drinking coffee because it can be used as a stress reliever, \n" +
                "it is proven to be good for your health and it is accommodating.");
        text.add("Cupcakes are small, tasty snack cakes that are favored for their portability and portion-control. \n" +
                "They are batter cakes baked in a cup-shaped foil or temperature resistant paper.");
        text.add("Honey is a sweet, viscous liquid food, dark golden in colour, \n" +
                "produced in the honey sacs of various bees from the nectar of flowers. \n" +
                "Flavour and colour are determined by the flowers from which the nectar is gathered. \n" +
                "Some of the most commercially desirable honeys are produced from clover by the domestic honeybee.");
        text.add("Pizza is an Italian food that was created in Italy. \n" +
                "It is made with different toppings. Some of the most common toppings are cheese, \n" +
                "sausages, pepperoni, vegetables, tomatoes, spices and herbs and basil. \n" +
                "These toppings are added over a piece of bread covered with sauce.");
        text.add("An apple pie is a fruit pie in which the principal filling ingredient is apples. \n" +
                "Apple pie is often served with whipped cream, ice cream, custard or cheddar cheese.");
        text.add("A salad is a serving in a meal that includes leaf vegetables such as lettuce, \n" +
                "spinach, or arugula. Uncooked or cold cooked vegetables that are sliced into small pieces \n" +
                "(for example tomato or onion) are then mixed with the leaf vegetables.");

        int randomIndex = random.nextInt(text.size());

        textModel = new TextModel(text.get(randomIndex));

        return textModel;
    }

    @Override
    public ArrayList<String> getAnswer() {
        return answer;
    }
}
