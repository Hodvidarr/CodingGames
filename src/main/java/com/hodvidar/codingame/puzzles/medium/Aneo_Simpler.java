package com.hodvidar.codingame.puzzles.medium;
/*
Vous entrez sur une portion de route et vous comptez vous reposer entièrement sur votre régulateur de vitesse pour traverser la zone sans devoir vous arrêter ni ralentir.

L'objectif est de trouver la vitesse maximale (hors excès de vitesse) qui vous permettra de franchir tous les feux au vert.

Attention : Vous ne pouvez pas franchir un feu à la seconde où il passe au rouge !

Votre véhicule entre directement dans la zone à la vitesse programmée sur le régulateur et ce dernier veille à ce qu'elle ne change plus par la suite.
Input
Ligne 1 : Un entier speed pour la vitesse maximale autorisée sur la portion de route (en km/h).

Ligne 2 : Un entier lightCount pour le nombre de feu de circulation sur la route.

lightCount prochaines lignes :
- Un entier distance représentant la distance du feu par rapport au point de départ (en mètre).
- Un entier duration représentant la durée du feu sur chaque couleur.

Un feu alterne une période de duration secondes au vert puis duration secondes au rouge.
Tous les feux passent au vert en même temps dès votre entrée dans la zone.
Output
Ligne 1 : La vitesse entière (km/h) la plus élevée possible qui permet de franchir tous les feux au vert sans commettre d'excès de vitesse.
*/

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * https://www.codingame.com/ide/puzzle/aneo
 * by Hodvidar
 * SpeedCalculator#getDuration(...)
 * Using : / (18d/5d)
 * - (int) Math.round(distance/ ( speedInkmPerHour/ (18d/5d) ) ); --> 100% Given tests / 80% Validator tests
 * - (int) Math.floor(distance/ ( speedInkmPerHour/ (18d/5d) ) ); --> 80% Given tests / 90% Validator tests
 * - (int) Math.ceil(distance/ ( speedInkmPerHour/ (18d/5d) ) ); --> 80% Given tests / 80% Validator tests
 * - (int) (distance/ ( speedInkmPerHour/ (18d/5d) ) ); --> 80% Given tests / 90% Validator tests
 * or * (5d/18d)
 * - (int) Math.round(distance/ ( speedInkmPerHour * (5d/18d) ) ); 100% Given tests / 80% Validator tests
 * - (int) Math.floor(distance/ ( speedInkmPerHour * (5d/18d) ) ); 80% Given tests / 90% Validator tests
 * - (int) Math.ceil(distance/ ( speedInkmPerHour * (5d/18d) ) ); 80% Given tests / 80% Validator tests
 * - (int) (distance/ ( speedInkmPerHour * (5d/18d) ) ); 80% Given tests / 90% Validator tests
 **/
class Aneo_Simpler {
    private static final boolean TESTING = true;
    private static final boolean VERBOSE = false;

    private static final JeuxDeTest[] tests = new JeuxDeTest[]{JeuxDeTest.FeuDuVillage,
            JeuxDeTest.FeuDuVillage2, JeuxDeTest.RouteDeCampagneTranquille,
            JeuxDeTest.RouteDeCampagneMoinsTranquille,
            JeuxDeTest.RouteDeCampagneTranquilleDereglee, JeuxDeTest.RouteDeCampagneSTPC,
            JeuxDeTest.AutorouteAllemande, JeuxDeTest.PluieDeFeux, JeuxDeTest.GuirlandeLumineuse,
            JeuxDeTest.FeuxRapides};

    public static void main(final String[] args) {
        System.err.println("Solution using a Set of validated speeds and convertion : (int) Math.round(d/v)");

        final Aneo_Simpler s = new Aneo_Simpler();
        if (!TESTING)
            s.doSolution();
        else {
            for (final JeuxDeTest test : tests)
                s.doSolution(test);
        }
    }

    public static void printIfVerbose(final String s) {
        if (VERBOSE)
            System.err.println(s);
    }

    private void doSolution(final JeuxDeTest jeuDeTest) {
        final int speed = jeuDeTest.speed;
        final int lightCount = jeuDeTest.lightCount;

        printIfVerbose("----------- Test '" + jeuDeTest + "' -----------");

        printIfVerbose("speed=" + speed);
        printIfVerbose("lightCount=" + lightCount);

        final SpeedCalculator calculator = new SpeedCalculator(speed);
        for (int i = 0; i < lightCount; i++) {
            final int distance = jeuDeTest.distances[i];
            final int duration = jeuDeTest.durations[i];
            printIfVerbose("distance=" + distance);
            printIfVerbose("duration= " + duration);
            final RoadSegment segment = new RoadSegment(distance, duration);
            calculator.addRoadSegment(segment);
            printIfVerbose("Possible max speed = " + calculator.getMaxSpeed());
        }

        final int expected = jeuDeTest.result;
        final int actual = calculator.getMaxSpeed();

        printIfVerbose("Final result expected : " + expected);
        printIfVerbose("Final result actual : " + actual);

        if (actual != expected)
            System.err.println("JeuxDeTest '" + jeuDeTest + " expected=" + expected + " actual="
                    + actual + " --> FAILURE");
        else
            printIfVerbose("JeuxDeTest '" + jeuDeTest + "' expected=" + expected
                    + " actual=" + actual + " -->  SUCCESS");
    }

    private void doSolution() {
        final Scanner in = new Scanner(System.in);
        final int speed = in.nextInt();
        final int lightCount = in.nextInt();

        final SpeedCalculator calculator = new SpeedCalculator(speed);
        for (int i = 0; i < lightCount; i++) {
            final int distance = in.nextInt();
            final int duration = in.nextInt();
            final RoadSegment segment = new RoadSegment(distance, duration);
            calculator.addRoadSegment(segment);
        }

        final int actual = calculator.getMaxSpeed();
        System.out.println(actual);
        in.close();
    }

    // --------------------------------- INTERNAL CLASSES --------------------------------------------

    enum JeuxDeTest {
        FeuDuVillage(50, 1, new int[]{200}, new int[]{15}, 50),
        FeuDuVillage2(50, 1, new int[]{200}, new int[]{10}, 36),
        RouteDeCampagneTranquille(90, 3, new int[]{300, 1500, 3000}, new int[]{30, 30, 30}, 90),
        RouteDeCampagneMoinsTranquille(90, 3, new int[]{300, 1500, 3000}, new int[]{10, 10, 10}, 54),
        RouteDeCampagneTranquilleDereglee(90, 3, new int[]{300, 1500, 3000}, new int[]{30, 20, 10}, 67),
        RouteDeCampagneSTPC(80, 4, new int[]{700, 2200, 3000, 4000}, new int[]{25, 15, 10, 28}, 49),
        AutorouteAllemande(200, 6, new int[]{1000, 3000, 4000, 5000, 6000, 7000}, new int[]{15,
                10, 30, 30, 5, 10}, 60),
        PluieDeFeux(130, 100, new int[]{500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000,
                5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000, 10500, 11000, 11500,
                12000, 12500, 13000, 13500, 14000, 14500, 15000, 15500, 16000, 16500, 17000, 17500,
                18000, 18500, 19000, 19500, 20000, 20500, 21000, 21500, 22000, 22500, 23000, 23500,
                24000, 24500, 25000, 25500, 26000, 26500, 27000, 27500, 28000, 28500, 29000, 29500,
                30000, 30500, 31000, 31500, 32000, 32500, 33000, 33500, 34000, 34500, 35000, 35500,
                36000, 36500, 37000, 37500, 38000, 38500, 39000, 39500, 40000, 40500, 41000, 41500,
                42000, 42500, 43000, 43500, 44000, 44500, 45000, 45500, 46000, 46500, 47000, 47500,
                48000, 48500, 49000, 49500, 50000}, new int[]{15, 15, 15, 15, 15, 15, 15, 15, 15,
                15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
                15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
                15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
                15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
                15, 15, 15}, 60),
        GuirlandeLumineuse(130, 100, new int[]{1100, 1150, 1200, 1250, 1300, 2100, 2150, 2200, 2250,
                2300, 3100, 3150, 3200, 3250, 3300, 4100, 4150, 4200, 4250, 4300, 5100, 5150, 5200,
                5250, 5300, 6100, 6150, 6200, 6250, 6300, 7100, 7150, 7200, 7250, 7300, 8100, 8150,
                8200, 8250, 8300, 9100, 9150, 9200, 9250, 9300, 10100, 10150, 10200, 10250, 10300,
                11100, 11150, 11200, 11250, 11300, 12100, 12150, 12200, 12250, 12300, 13100, 13150,
                13200, 13250, 13300, 14100, 14150, 14200, 14250, 14300, 15100, 15150, 15200, 15250,
                15300, 16100, 16150, 16200, 16250, 16300, 17100, 17150, 17200, 17250, 17300, 18100,
                18150, 18200, 18250, 18300, 19100, 19150, 19200, 19250, 19300, 20100, 20150, 20200,
                20250, 20300}, new int[]{10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30,
                10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15,
                20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25,
                30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10,
                15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30, 10, 15, 20, 25, 30}, 6),
        FeuxRapides(90, 16, new int[]{1234, 2468, 3702, 6170, 8638, 13574, 16042, 20978, 23446,
                28382, 35786, 38254, 45658, 50594, 53062, 57988}, new int[]{5, 5, 5, 5, 5, 5, 5, 5,
                5, 5, 5, 5, 5, 5, 5, 5}, 74);

        public final int speed;
        public final int lightCount;
        public final int[] distances;
        public final int[] durations;
        public final int result;

        JeuxDeTest(final int speed, final int lightCount, final int[] distances, final int[] durations, final int result) {
            this.speed = speed;
            this.lightCount = lightCount;
            this.distances = distances;
            this.durations = durations;
            this.result = result;
        }
    }

    class RoadSegment {
        public final int distance;
        public final int duration;

        public RoadSegment(final int distance, final int duration) {
            this.distance = distance;
            this.duration = duration;
        }
    }

    class SpeedCalculator {
        private final Set<Integer> validatedSpeed = new HashSet<>();
        /**
         * in km/h
         */
        private int min = 1;
        /**
         * in km/h
         */
        private int max;

        public SpeedCalculator(final int maximumSpeed) {
            printIfVerbose("NEW SpeedCalculator with max speed: " + maximumSpeed + " km/h");
            this.max = maximumSpeed;
            for (int i = this.min; i <= this.max; i++) {
                validatedSpeed.add(Integer.valueOf(i));
            }
        }

        public int getMaxSpeed() {
            return this.max;
        }

        /**
         * Adapts the possibleSpeed intervals to handle this new segment. <br/>
         * Also adapts the min and max authorized values.
         */
        public void addRoadSegment(final RoadSegment aRoadSegment) {
            printIfVerbose("addRoadSegment(" + aRoadSegment.distance + ", "
                    + aRoadSegment.duration + ")");

            this.modifyValidatedSpeedsForNewSegment(aRoadSegment);

        }

        /**
         * Remove speeds that pass a light when it is red. So we have less and less speed to check.
         *
         * @param aRoadSegment
         * @return
         */
        private void modifyValidatedSpeedsForNewSegment(final RoadSegment aRoadSegment) {
            final Iterator<Integer> speedIte = this.validatedSpeed.iterator();
            while (speedIte.hasNext()) {
                final Integer speed = speedIte.next();
                final int timeToPass = this.getDuration(aRoadSegment.distance, speed);
                final boolean passGreen = this.isGreen(aRoadSegment.duration, timeToPass);

                if (!passGreen)
                    speedIte.remove();
            }

            int min = this.max;
            int max = -1;
            for (final int i : this.validatedSpeed) {
                if (i < min)
                    min = i;
                if (i > max)
                    max = i;
            }

            this.min = min;
            this.max = max;
        }

        /**
         * Returns time (in seconds) to travel the given distance and the given speed. <br/>
         * (Uses Math.floor and return an int).
         *
         * @param distance
         * @param speedInkmPerHour
         * @return
         */
        private int getDuration(final int distance, final int speedInkmPerHour) {
            return (int) Math.round(distance / (speedInkmPerHour / (18d / 5d)));
        }

        /**
         * Returns if time is when light is green. <br/>
         * Exclude instant when green -> red <br/>
         * Include instant when red -> green.
         *
         * @param lightPeriod (in seconds) green -> red -> green -> red
         * @param time        (in seconds)
         * @return
         */
        private boolean isGreen(final int lightPeriod, final int time) {
            if (time < lightPeriod)
                return true; // Pass before green -> red, OK
            if (time == lightPeriod)
                return false; // Pass when green -> red, not OK
            if (time < lightPeriod * 2)
                return false; // Pass before red -> green not OK

            return isGreen(lightPeriod, time - (lightPeriod * 2));
        }
    }
}

