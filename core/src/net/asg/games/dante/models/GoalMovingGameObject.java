/**
 *
 */
package net.asg.games.dante.models;

import net.asg.games.dante.Constants;
import net.asg.games.dante.providers.ImageProvider;
import net.asg.games.dante.screens.GameScreenState.LevelState;
import net.asg.games.dante.providers.SoundProvider;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Blakbro2k
 */
public class GoalMovingGameObject extends MovingGameObject {
    private int velocityX = Constants.GOAL_OBJECT_X_VELOCITY;
    // private int animationPattern[];

    public GoalMovingGameObject(ImageProvider imageProvider,
                                TextureRegion[] textureRegions, SoundProvider soundProvider,
                                int width, int height, boolean isHitboxActive, MovingGameObjectState state) {
        super(imageProvider, textureRegions, soundProvider, width, height,
                isHitboxActive, state);
    }

    public void moveLeft(float delta, float speedBonus) {
        int moveSpeed = Constants.GOAL_OBJECT_MOVE_SPEED;
        rect.x -= moveSpeed * delta;
        state.setPosX((int) rect.x);
        time += delta;
        if (time > animationPeriod) {
            time -= animationPeriod;
            frame += 1;
            if (frame >= textureRegions.length) {
                frame = 0;
            }
        }
    }

    public LevelState doCollision(float delta) {
        if (!isSoundTriggered) {
            soundProvider.playGoalHitSound();
            state.setSoundTriggered(isSoundTriggered = true);
        }

        rect.y -= 1300 * delta;
        rect.x += velocityX * delta;
        state.setPosX((int) rect.x);
        state.setPosX((int) rect.y);
        velocityX -= 1;
        return LevelState.GOALHIT;
    }
}
