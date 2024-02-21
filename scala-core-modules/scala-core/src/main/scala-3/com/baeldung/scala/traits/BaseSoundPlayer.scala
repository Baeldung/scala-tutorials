// Example of a trait:

trait BaseSoundPlayer {
  def play
  def close
  def pause
  def stop
  def resume
}

class Mp3SoundPlayer extends BaseSoundPlayer {
  def play {}
  def close {}
  def pause {}
  def stop {}
  def resume {}
}
